package com.etiyacrm.customerservice.core.business.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageInfo {//Tüm datatyı çekmesin onun yerine ihtiyacı olan datayı çeksin diye bu class yazıldı
    private int page = 0;
    private int size = 10;
}
