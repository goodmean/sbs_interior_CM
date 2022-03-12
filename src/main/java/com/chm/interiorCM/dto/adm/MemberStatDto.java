package com.chm.interiorCM.dto.adm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberStatDto {

	private Long totalMemberCount;
	private Long todaySignMemberCount;

	private Long adminMemberCount;
	private Long userMemberCount;

}
