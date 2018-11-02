package com.flwm.common.domain;

import com.flwm.common.util.DateUtil;
import lombok.Data;

import java.util.List;

/**
 * Created by zhoupj on 10/29/18.
 */
@Data
public class FinRequest extends PageRequest {


    private String finDate;
    //funding
    private Double fundHolding;
    //
    private Integer isexpected;
    //ssr2
    private Double ssr2;
    //ssr8
    private Double ssr8;
    //sb持仓
    private Double sbHolding;

    private String code;

    private List<String> codes;

    private Double yyzsrtbzz;

    private Double gsjlrtbzz;

    private Double kfjlrtbzz;

    private Integer finType;


    public FinRequest() {

    }

    public FinRequest(SearchRequest request) {

        this.finDate = DateUtil.getReportDate(request.getTradeDate());
        this.fundHolding = request.getFundHolding();

        this.ssr2 = request.getSsr2();


        this.code = request.getCode();
        this.finType = 1;

    }

    public FinRequest(SearchRequest request, List<String> codes) {

        this.finDate = DateUtil.getReportDate(request.getTradeDate());
        this.fundHolding = request.getFundHolding();

        this.ssr2 = request.getSsr2();

        if (codes != null && codes.size() > 0) {
            this.codes = codes;
        }

        this.finType = 1;

    }
}
