package com.codeworks.mvcweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeworks.model.Student;
import com.codeworks.service.StudentService;
import com.codeworks.service.UserService;

@Controller
@RequestMapping("/user")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/verifyCode",method=RequestMethod.GET)
	//public ResponseEntity<List<Student>> registerStudent(@ModelAttribute("studForm") Student studentForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request) {
	public ResponseEntity<String> getStudentFromEmailId(@RequestParam("emailId") String emailId,@RequestParam("inviteCode") String inviteCode,HttpServletRequest request){
		/*Student student = studentService.getStudentFromEmailId(emailId);
		return new ResponseEntity(student, HttpStatus.OK);*/
		HttpSession session = request.getSession();
		Student student = (Student)session.getAttribute(emailId);
		String message = "&nbsp;Invite code does not match with this email id. Please contact your instructor for help.";
		if(student != null && student.getStudentId()>0){
			if(StringUtils.equalsIgnoreCase(emailId,student.getEmailId()) && StringUtils.equalsIgnoreCase(inviteCode,student.getInviteCode())){
				//Check the email id existence in user table
				request.setAttribute("iCode", emailId+":"+student.getInviteCode());
				message = "valid";
			} 
		}
		
		
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/emailEnrolled",method=RequestMethod.GET)
	//public ResponseEntity<List<Student>> registerStudent(@ModelAttribute("studForm") Student studentForm, BindingResult bindingResult,Model model, String error, String logout,HttpServletRequest request) {
	public ResponseEntity<String> emailIdExists(@RequestParam("emailId") String emailId,HttpServletRequest request){
		Student student =  studentService.verifyUserExistence(emailId); //studentService.getStudentFromEmailId(emailId);
		HttpSession session = request.getSession();
		String message = "&nbsp;Email Id(User) not enrolled for self registration. Please contact your instructor for help.";
		session.setAttribute(emailId, student);
		if(student.isStudExists() && student.getStudentId()>0){
			
			if(!student.isUserExists()) {
				if(StringUtils.equalsIgnoreCase(emailId,student.getEmailId())){
					request.setAttribute("iCode", emailId+":"+student.getInviteCode());
					message = "valid";
				} 
			} else {
				message = "Email Id already registered as a user. Please contact your instructor for help";
			}
			
		} else {
			message = "Student not enrolled. Please contact your instructor for help";
		}
		
		
		return new ResponseEntity(message, HttpStatus.OK);
	}
	

}
