package com.flwm.common.util;

import com.flwm.common.VO.SearchVO;
import com.flwm.common.domain.SearchRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterUtil {



    public static List<SearchVO> filterPage(List<SearchVO> searchVOS,SearchRequest request) {

        int offset = request.getOffset();

        if (offset >= searchVOS.size()) {
            return new ArrayList<>();
        }

        if (offset + request.getPageSize() >= searchVOS.size()) {
            return searchVOS.subList(offset, searchVOS.size());
        } else {
            return searchVOS.subList(offset, offset + request.getPageSize());
        }


    }


    public static List<SearchVO> filterData(List<SearchVO> searchVOS, SearchRequest request) {


        if(searchVOS==null || searchVOS.size()==0){
            return searchVOS;
        }

        return searchVOS.parallelStream()
                .filter(p->request.getCode()==null || p.getCode().equals(request.getCode()))
                .filter(p -> compareLessEqual(request.getDifftohigh250(), p.getDifftohigh250()))
                .filter(p -> compareLessEqual(request.getDifftohigh120(), p.getDifftohigh120()))
                .filter(p -> compareLessEqual(request.getFluof250d(), p.getFluof250d()))
                .filter(p -> compareGreaterEqual(request.getFundHolding(), p.getFundHolding()))
                .filter(p -> compareGreaterEqual(request.getHkHoldingAmount(), p.getHkHoldingAmount()))
                .filter(p -> request.getIsMR() == null || p.getIsMR() != null && p.getIsMR() == request.getIsMR())
                .filter(p -> compareGreaterEqual(request.getRps250(), p.getRps250()))
                .filter(p -> compareGreaterEqual(request.getRps120(), p.getRps120()))
                .filter(p -> compareGreaterEqual(request.getRps50(), p.getRps50()))
                .filter(p -> compareGreaterEqual(request.getSsr2(), p.getSsr2()))
                .filter(p -> compareLessEqual(request.getTurn(), p.getTurn()))
                .filter(p -> request.getGy() == null || p.getMa250() != null && p.getClose() >= p.getMa250())
                .sorted(Comparator.comparing(SearchVO::getCode)).collect(Collectors.toList());
    }

    private static boolean compareGreaterEqual(Double request, Double actual) {

        return request == null || (actual != null && actual.doubleValue() >= request);

    }

    private static boolean compareLessEqual(Double request, Double actual) {

        return request == null || (actual != null && actual.doubleValue() <= request);
    }


}
