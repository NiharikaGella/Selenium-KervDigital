Feature: Visit a page and click on a link

  @test
  Scenario: Kerv Digital
    Given Navigate to kerv Digital site using <url> 
    And Scroll down to get rid of cookie warning
    When User opens right-side menu and click on Careers page
    And Clicks on ‘Look for Job opportunities’
    And Searches for UX designer
    And Open the first opening, Fill the details and submit
    Then User message should be submitted successfully 
 
       Examples:
   |url| 
   |https://kerv.com|
	
	
	
	
	


    
    
    



