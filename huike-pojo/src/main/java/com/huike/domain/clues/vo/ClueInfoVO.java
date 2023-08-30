package com.huike.domain.clues.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huike.domain.clues.TbClue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClueInfoVO extends TbClue {

    //线索归属
    private String assignUserName;

    //归属时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date lastAssignTime;
}
