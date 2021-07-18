package com.yugioh.yugioh.dtos.ygo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class YGOCollection {
	private List<YGOCard> data;
}
