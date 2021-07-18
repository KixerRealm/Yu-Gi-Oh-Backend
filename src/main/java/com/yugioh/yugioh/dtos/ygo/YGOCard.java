package com.yugioh.yugioh.dtos.ygo;

import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YGOCard {
    private String id;
    private String type;
    private String name;
    private String desc;
    private Integer atk;
    private Integer def;
}
