package com.sundogsoftware.sparkstreaming

import org.apache.log4j.Level
import java.util.regex.Pattern
import java.util.regex.Matcher

object Utilities {
    /** Makes sure only ERROR messages get logged to avoid log spam. */
  def setupLogging() = {
    import org.apache.log4j.{Level, Logger}   
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)   
  }
  
  /** Configures Twitter service credentials using twiter.txt in the main workspace directory */
  def setupTwitter() = {
    import scala.io.Source
    
    for (line <- Source.fromFile("twitter.txt").getLines) {
      val fields = line.split(" ")
      if (fields.length == 2) {
        System.setProperty("twitter4j.oauth." + fields(0), fields(1))
      }
    }
  }
  
  /** Retrieves a regex Pattern for parsing Apache access logs. */
  //Help here
  //The apche log format is this http://stackoverflow.com/questions/9234699/understanding-apache-access-log
  
  def apacheLogPattern():Pattern = {
    val ddd = "\\d{1,3}"                 //matches 1,2 or 3 digits     
    val ip = s"($ddd\\.$ddd\\.$ddd\\.$ddd)?"  
    val client = "(\\S+)"                     //Matches 1 or more nonwhitespace characters.
    val user = "(\\S+)"
    val dateTime = "(\\[.+?\\])"  //Starts with a bracket, contains one or more characters (because of teleia which matches any character and +, which mathes one or more) and ends with a bracket. Because we have + insted of * this means that datatime inside brackets cannot be empty         
    val request = "\"(.*?)\""     //Starts with a autaki"", contains zero or more characters (because of teleia which matches any character and *, which mathes zero or more) and ends with autaki. Because we have * insted of + this means that request inside autakia can be empty                
    val status = "(\\d{3})"      
    val bytes = "(\\S+)"                     
    val referer = "\"(.*?)\""
    val agent = "\"(.*?)\""
    val regex = s"$ip $client $user $dateTime $request $status $bytes $referer $agent"
    Pattern.compile(regex)    
    
    
    

  }
}
