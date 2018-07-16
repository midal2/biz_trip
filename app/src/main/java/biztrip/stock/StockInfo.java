package biztrip.stock;

import java.util.Date;

import lombok.Data;


/**
 * 증권기본정보
 */
@Data
public class StockInfo {
    private String stockCd; //종목코드
    private String stockNm; //종목명
    private Date updateDt; //최종갱신시간
    private long nowPrice; //현재가
}
