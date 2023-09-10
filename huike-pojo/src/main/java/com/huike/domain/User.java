package com.huike.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zxj
 * @mail zxjOvO@gmail.com
 * @date 2023/09/10 17:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String date;
    private Integer newClueCount;
    private Integer newBusinessCount;
    private Integer newContractCount;
    private Integer salesCount;

}
