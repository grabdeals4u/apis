/**
 * 
 */
package com.notificationservice.apis;

import javax.management.Notification;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL
 *
 */
@RestController
@RequestMapping("/notifications")
public class NotificationsController {

	@PostMapping(name="/notify/{topic}")
	public void notify(@PathVariable String topic){
		
	}
	
	
}
