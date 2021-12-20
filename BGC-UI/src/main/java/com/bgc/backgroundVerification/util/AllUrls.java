package com.bgc.backgroundVerification.util;

public class AllUrls {
	
	//public static final String url="https://bgcwebservices.azurewebsites.net";
	
	
	public static final String url="http://localhost:9091";
	
	//Forgot password link for admin and employee
	
	public static final String ForgotPasswordLink=url+"/forgetPassword/";
	
	public static final String ForgotPassword=url+"/forgetPassword/verifylink";
	
	//Forgot password link for candidates
	public static final String CandidateForgotPasswordLink=url+"/forgetPassword/candidate/";
	
	public static final String CandidateForgotPassword=url+"/forgetPassword/candidate/verifylink";
	
	public static final String ForgotUserNameLink=url+"/forgetUserName";
	
	
	
	public static final String GetLoginUserDetails=url+"/login/userdetails/";
	
	public static final String GetLoginCandidateDetails=url+"/login/candidatesdetails/";
	
	public static final String GenerateToken=url+"/login/generateToken";
	
	public static final String Setup=url+"/setup/";
	
	public static final String GetAprofile=url+"/getAprofile/";
	
	public static final String GetAllEmployees=url+"/getListOfEmployees/";
	
	public static final String GetAllCustomer=url+"/getListOfCustomer";
	
	public static final String AddEmployees=url+"/addEmployee";
	
	public static final String UpdateEmployees=url+"/updateEmployee";
	
	public static final String DEleteEmployees=url+"/deleteEmployee/";
	
	public static final String GetEmployeeDetails=url+"/getEmployeeDetails/";
	
	
	//Candidates Urls
	public static final String GetAllCandidates=url+"/getListOfCanditates/";
	
	public static final String AddCandidate=url+"/addCandidate";
	
	public static final String GetCandidateDetail=url+"/getCanditatesDetails/";
	
	public static final String UpdateCandidate=url+"/updateCandidate";
	
	public static final String UplodeCandidateResume=url+"/uplodeCandidateResume";
	
	public static final String DeleteCandidate=url+"/deletecandidate/";
	
	public static final String EducationInfoList=url+"/educationInfoList/";
	
	public static final String EducationInfo=url+"/educationInfo/";
	
	public static final String AddEducationInfo=url+"/addEducationInfo";
	
	public static final String UpdateEducationInfo=url+"/updateEducationInfo";
	
	public static final String DeleteEducationInfo=url+"/deleteEducationInfo/";
	
	public static final String CandidateGeneralInfo=url+"/candidategeneralInfo/";
	
	public static final String UpdateCandidateGeneralInfo=url+"/updateCandidategeneralInfo";
	
	public static final String GetExperienceList=url+"/ExperienceList/";
	
	public static final String GetExperienceInfo=url+"/ExperienceInfo/";
	
	public static final String AddExperience=url+"/addExperience";
	
	public static final String UpdateExperience=url+"/updateExperience";
	
	public static final String DeleteExperience=url+"/DeleteExperience/";
	
	
	
	
}
