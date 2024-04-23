package com.vitacure.doctorservice.util;

import java.util.ArrayList;
import java.util.List;

import com.vitacure.doctorservice.model.MedicalRepresentative;
import com.vitacure.doctorservice.model.MrWrapper;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

public class MrUtil {
	
	public static List<MrWrapper> convertMrListToMrWrapperList(List<MedicalRepresentative> list){
		List<MrWrapper> newList = new ArrayList<>();
		for(MedicalRepresentative mr : list) {
			newList.add(convertMedicalRepresentativeToMrWrapper(mr));
		}
		return newList;
	}
	
	public static MrWrapper convertMedicalRepresentativeToMrWrapper(MedicalRepresentative mr) {
		return new MrWrapper(
				mr.getMrId(),
				mr.getName(),
				mr.getPhoneNumber(),
				mr.getEmail(),
				mr.getWorkLocation()
			);
	}
}
