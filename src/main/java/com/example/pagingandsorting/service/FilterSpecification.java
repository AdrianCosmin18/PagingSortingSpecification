package com.example.pagingandsorting.service;

import com.example.pagingandsorting.dto.RequestDto;
import com.example.pagingandsorting.dto.SearchRequestDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilterSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto){

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> list, RequestDto.GlobalOperator globalOperator){

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            for(SearchRequestDto s: list){
                predicates.add(criteriaBuilder.equal(root.get(s.getColumn()), s.getValue()));
            }

            if(globalOperator.equals(RequestDto.GlobalOperator.AND)){
                // asa combina toate predicatele din lista cu and (incepand cu primul predicat, le concateneaza pe toate)
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }else {
                // asa combina toate predicatele din lista cu or (incepand cu primul predicat, le concateneaza pe toate)
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    public Specification<T> getSearchSpecification2(List<SearchRequestDto> list, RequestDto.GlobalOperator globalOperator){

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            for(SearchRequestDto searchRequestDto: list){

                switch (searchRequestDto.getOperation()){

                    case EQUAL:{
                        Predicate equal = criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(equal);
                        break;
                    }
                    case LIKE:{
                        Predicate like = criteriaBuilder.like(root.get(searchRequestDto.getColumn()), "%" + searchRequestDto.getValue() + "%");
                        predicates.add(like);
                        break;
                    }
                    case IN:{
                        //primim o lista de nume, ex: "cosmin,mihai,laur" si vom vrea sa ii facem split la virgula

                        String[] split = searchRequestDto.getValue().split(",");
                        Predicate in = root.get(searchRequestDto.getColumn()).in(Arrays.asList(split));
                        predicates.add(in);
                        break;
                    }
                    case LESS_THAN:{
                        Predicate lessThan = criteriaBuilder.lessThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(lessThan);
                        break;
                    }
                    case GREATER_THAN:{
                        Predicate greaterThan = criteriaBuilder.greaterThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(greaterThan);
                        break;
                    }
                    case BETWEEN:{
                        //primim 2 valori: [10, 20]. vrem tot ce este intre aceste valori
                        String[] split = searchRequestDto.getValue().split(",");
                        Predicate between = criteriaBuilder.between(root.get(searchRequestDto.getColumn()), Long.parseLong(split[0]),Long.parseLong(split[1]));
                        predicates.add(between);
                        break;
                    }
                    case JOIN:{
                        //avem relatia student <-> address, vrem sa luam date despre adresa studentului, asa folosim join
                        //criteriaBuilder.equal(root.join("joinTable").get("attributeFromJoinTable"), searchRequestDto.getValue());
                        Predicate join = criteriaBuilder.equal(root.join(searchRequestDto.getJoinTable()).get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(join);
                        break;
                    }
                    default:
                        throw new IllegalStateException("unexpected value");
                }
            }
            if(globalOperator.equals(RequestDto.GlobalOperator.AND)){
                // asa combina toate predicatele din lista cu and (incepand cu primul predicat, le concateneaza pe toate)
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }else {
                // asa combina toate predicatele din lista cu or (incepand cu primul predicat, le concateneaza pe toate)
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
