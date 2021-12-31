package com.atmos.oxygen.consumer;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.atmos.oxygen.config.RabbitConstants;
import com.atmos.oxygen.dto.HireStatus;

@Component
public class User {

	@Autowired
	private JavaMailSender javaMailSender;

	@RabbitListener(queues = RabbitConstants.QUEUE)
	public void consumeMessageFromQueue(List<HireStatus> hireStatus) throws InterruptedException {

		hireStatus.stream().forEach((hs) -> {
			System.out.println("Message Received from Queue " + hs.getCandidate().getName() + " received");
			sendEmail(hs);
			System.out.println("Result Email Send Successfully");
		});

	}

	void sendEmail(HireStatus hireStatus) {

		SimpleMailMessage msg = new SimpleMailMessage();

		Object[] params = new Object[] { hireStatus.getCandidate().getName(), hireStatus.getCandidate().getCompany(),
				hireStatus.getCandidate().getDesignation() };
		String mailText = "";

		msg.setTo(hireStatus.getCandidate().getEmail());
		msg.setSubject("Job Update from " + hireStatus.getCandidate().getCompany());

		if (hireStatus.isHired()) {
			mailText = MessageFormat.format(
					"Dear {0},\nCongratulations from {1} ! We are really excited to select your candidature for the position of {2} at {1}. Looking forward to have you on-board.\nFurther details related to onboarding process and compensation will be shared in a separate mail.\n Welcome to {1}\nRegards,\nTalent Acquisition Team,\n{1}",
					params);

		} else {

			mailText = MessageFormat.format(
					"Dear {0},\n Thank you for applying to {1}. After considering a lot of applications, we are no longer considering your profile for this role. Best of luck for your future endeavours. \nRegards,\n Talent Acquisition Team,\n{1}",
					params);
			msg.setText(
					"Unfortunately, We are no longer moving forward with your application. Thank you for your patience. \n Regards, \n Talent Acquisition Team \n "
							+ hireStatus.getCandidate().getCompany());

		}
		msg.setText(mailText);
		javaMailSender.send(msg);

	}

}
