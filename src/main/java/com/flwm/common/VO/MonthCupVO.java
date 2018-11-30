package com.flwm.common.VO;

import lombok.Data;

@Data
public class MonthCupVO {


    private String month;

    private double succRate;

    private double avgWinYieldRate;

    private double avgLossYieldRate;

    private double cupV;
}
