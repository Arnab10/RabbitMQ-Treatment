package com.atmos.oxygen.producer;

import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atmos.oxygen.config.RabbitConstants;
import com.atmos.oxygen.dto.Candidate;
import com.atmos.oxygen.dto.HireStatus;
import com.atmos.oxygen.repository.CandidateRepository;

@RestController
@RequestMapping("/newhiring")
public class HireProducer {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private CandidateRepository repo;

	@PostMapping("/addApplicant")
	public String addApplicant(@RequestBody Candidate candidate) {

		candidate.setApplicantId(UUID.randomUUID().toString());
		System.out.println("prpocessing for save. . .");

		repo.save(candidate);

		System.out.println("Save Successful");
		System.out.println(candidate.getName() + " Offered the job to " + candidate.getCompany());

		return "Operation successful.";

	}

	@PostMapping("/processApplicant")
	public String processApplicant(@RequestBody List<HireStatus> statusList) {
		repo.deleteAll();
		template.convertAndSend(RabbitConstants.EXCHANGE, RabbitConstants.ROUTING_KEY, statusList);
		return "Success";

	}

	@GetMapping("/names/{name}")
	public String print(@PathVariable String name) {
		return "Hello " + name + ", don't worry, you'll do it";
	}

}
