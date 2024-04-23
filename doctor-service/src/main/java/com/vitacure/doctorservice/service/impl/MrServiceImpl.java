package com.vitacure.doctorservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitacure.doctorservice.exception.ResourceNotFoundException;
import com.vitacure.doctorservice.model.MedicalRepresentative;
import com.vitacure.doctorservice.model.MrWrapper;
import com.vitacure.doctorservice.repository.MrRepository;
import com.vitacure.doctorservice.service.MrService;
import com.vitacure.doctorservice.util.MrUtil;

import jakarta.validation.Valid;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Service
public class MrServiceImpl implements MrService {
	
	@Autowired
	private MrRepository mrRepo;
	
	@Override
	public MrWrapper createMr(MedicalRepresentative mr) {
		MedicalRepresentative newMr = mrRepo.save(mr);
		if(newMr == null) return null;
		return MrUtil.convertMedicalRepresentativeToMrWrapper(newMr);
	}
	
	@Override
	public MrWrapper getMrById(int id) throws ResourceNotFoundException {
		MedicalRepresentative mr = mrRepo.findById(id).orElse(null);
		if(mr == null) {
			throw new ResourceNotFoundException("Medical Representative Id", id);
		}
		return MrUtil.convertMedicalRepresentativeToMrWrapper(mr);
	}
	
	@Override
	public MrWrapper getMrByEmail(String email) throws ResourceNotFoundException {
		MedicalRepresentative mr = mrRepo.findByEmail(email).orElse(null);
		if(mr == null) {
			throw new ResourceNotFoundException("Medical Representative Email", email);
		}
		return MrUtil.convertMedicalRepresentativeToMrWrapper(mr);
	}
	
	@Override
	public List<MrWrapper> getAllMrs() {
		return MrUtil.convertMrListToMrWrapperList(mrRepo.findAll());
	}
	
	@Override
	public MrWrapper updateMr(@Valid MedicalRepresentative mr) throws ResourceNotFoundException {
		Optional<MedicalRepresentative> existingMr = mrRepo.findById(mr.getMrId());
		MrWrapper responseObj = null;
		if(existingMr.isPresent()) {
			responseObj = MrUtil.convertMedicalRepresentativeToMrWrapper(mrRepo.save(mr));
		} else {
			throw new ResourceNotFoundException("Medical Representative Id", mr.getMrId());
		}
		return responseObj;
	}
	
	@Override
	public List<MrWrapper> getAllMrsByLocation(String workLocation) throws ResourceNotFoundException {
		List<MedicalRepresentative> res = mrRepo.findAllByWorkLocation(workLocation);
		if(res == null || res.isEmpty()) {
			throw new ResourceNotFoundException("Medical Representative Location", workLocation);
		}
		return MrUtil.convertMrListToMrWrapperList(res);
	}
}
