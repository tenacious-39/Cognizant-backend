package com.vitacure.doctorservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitacure.doctorservice.exception.ResourceNotFoundException;
import com.vitacure.doctorservice.model.Doctor;
import com.vitacure.doctorservice.model.DoctorWrapper;
import com.vitacure.doctorservice.repository.DoctorRepository;
import com.vitacure.doctorservice.service.DoctorService;
import com.vitacure.doctorservice.util.DoctorUtil;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	private DoctorRepository docRepo;

	 @Override
	public DoctorWrapper getDoctorById(int doctorId) throws ResourceNotFoundException {
		Doctor doctor = docRepo.findById(doctorId).orElse(null);
		if(doctor == null) {
			throw new ResourceNotFoundException("doctorId", doctorId);
		}
		return DoctorUtil.convertDoctorToDoctorWrapper(doctor);
	}
	 
	 @Override
	public DoctorWrapper addDoctor(Doctor doctor) {
		return DoctorUtil.convertDoctorToDoctorWrapper(docRepo.save(doctor));
	}
	 
	 @Override
	public List<DoctorWrapper> getAllDoctors() {
		List<Doctor> docList = docRepo.findAll();
		if(docList.isEmpty()) {
			return null;
		}
		return DoctorUtil.convertDoctorListToDoctorWrapperList(docList);
	}
	 
	 @Override
	public DoctorWrapper updateDoctor(Doctor doctor) throws ResourceNotFoundException {
		Doctor existingDoctor = docRepo.findById(doctor.getDoctorId()).orElse(null);
		if(existingDoctor == null) {
			throw new ResourceNotFoundException("doctorId", doctor.getDoctorId());
		}
		return DoctorUtil.convertDoctorToDoctorWrapper(docRepo.save(doctor));
	}
	 
	 @Override
	public List<DoctorWrapper> getAllDoctorsBySpecialization(String specs) throws ResourceNotFoundException {
		List<Doctor> docList = docRepo.findAllBySpecialization(specs);
		if(docList == null || docList.isEmpty()) {
			throw new ResourceNotFoundException("Specialization", specs);
		}
		return DoctorUtil.convertDoctorListToDoctorWrapperList(docList);
	}
}
