Feature: Example

  @Complated
  Scenario: Insider - Selenium Task
    # 1. Visit https://useinsider.com/ and check Insider home page is opened or not
    Given Open "useinsider.com/" page
    When Check, is URL True "useinsider.com/"
    #  2. Select the “Company” menu in the navigation bar, select “Careers” and check Career page, its Locations, Teams, and Life at Insider blocks are open or not
    When Click "wt-cli-accept-all-btn" Object by "id"
    When Click "//a[contains(text(), 'Company')]" Object by "xPath"
    When Click "//a[contains(text(), 'Careers')]" Object by "xPath"
    When Check "//h3[contains(text(),'Find your calling')]" Object by "xPath"
    When Check "//h3[contains(text(),'Our Locations')]" Object by "xPath"
    When Check "//h2[contains(text(),'Life at Insider')]" Object by "xPath"
    # 3. Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the presence of the job list
    Given Open "https://useinsider.com/careers/quality-assurance/" page
    When Check, is URL True "useinsider.com/careers/quality-assurance/"
    When Click "//a[contains(text(), 'See all QA jobs')]" Object by "xPath"
    When Wait for "3" seconds
    When Check "//span[@id='select2-filter-by-department-container'and@title='Quality Assurance']" Object by "xPath"
    When Click "//span[@id='select2-filter-by-location-container']/following-sibling::span[@role='presentation']" Object by "xPath"
    When Click "//li[text()='Istanbul, Turkey']" Object by "xPath"
    When Check "//span[@id='select2-filter-by-location-container'and@title='Istanbul, Turkey']" Object by "xPath"
    When Click "//span[@id='select2-filter-by-department-container']" Object by "xPath"
    When Click "//li[text()='Quality Assurance']" Object by "xPath"
    When Check "//span[@id='select2-filter-by-department-container'and@title='Quality Assurance']" Object by "xPath"
    When Wait for "5" seconds
    # 4. Check that all jobs’ Position contains “Quality Assurance”, Department contains “Quality Assurance”, and Location contains “Istanbul, Turkey”
    When Check "//div[@id='jobs-list']//span" elemets by "xPath" for "Quality Assurance" text
    When Check "//div[@id='jobs-list']//div[@class='position-location text-large']" elemets by "xPath" for "Istanbul, Turkey" text
    When Wait for "1" seconds
    # 5. Click the “View Role” button and check that this action redirects us to the Lever Application form page
    When Check "(//div[@id='jobs-list']//p[@class='position-title font-weight-bold'])[1]" Object by "xPath"
    When Scroll to "(//div[@id='jobs-list']//p[@class='position-title font-weight-bold'])[1]" Object by "xPath"
    When Wait "5" seconds over "(//div[@id='jobs-list']//p[@class='position-title font-weight-bold'])[1]" Object by "xPath"
    When Wait "3" seconds over "(//div[@id='jobs-list']//a)[1]" Object by "xPath"
    When Click "(//div[@id='jobs-list']//a)[1]" Object by "xPath"
    When Wait for "1" seconds
    Given Switch the page
    When Check "//div[@class='message-buttons cc-desktop']/button[text()='Dismiss']" Object by "xPath"
    When Click "//div[@class='message-buttons cc-desktop']/button[text()='Dismiss']" Object by "xPath"
    When Wait for "2" seconds
    When Check "(//a[text()='Apply for this job'])[1]" Object by "xPath"
    Given Finish the scenario


















