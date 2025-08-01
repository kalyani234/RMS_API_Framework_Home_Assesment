# 📡 BBC RMS API Automation Framework – Home Assessment

This is a Java-based API testing framework developed as part of the **BBC Software Engineer in Test Take-Home Assessment**.  
It validates the **BBC RMS (Radio and Music Services)** API endpoint using BDD with Cucumber, REST Assured, Jackson, and JUnit.



---

## How It Works

1. **Feature File**  
   - Test cases are written using Gherkin syntax.  
   - Example:  
     ```gherkin
     Scenario: Verify GET request returns 200 status and response time  
       Given the RMS API is up and running  
       When I send a GET request to the RMS API  
       Then the response status code is 200
     ```

2. **Step Definition File (`RMS_API_StepDefs.java`)**  
   - Java methods are mapped to each Gherkin step.  
   - API responses are parsed and assertions are applied.

3. **Supporting Files**  
   - **Hooks**: Pre- and post-scenario logic  
   - **Utils**: Config loader, endpoint builder  
   - **POJOs**: Represent RMS API JSON schema using Lombok  
   - **Config**: Centralized configuration (base URL, thresholds)

4. **Execution Flow**  
   - Gherkin step → mapped to StepDefs → API call via RestAssured  
   - JSON parsed via ObjectMapper → validated → report generated

---

## Execution Instructions

1. Open terminal and navigate to project root directory (where `pom.xml` is located).
2. Run `mvn clean` to clear previous builds.
3. Run `mvn test` to compile and execute tests.
4. Reports will be generated at:
   - `target/cucumber/cucumber.json`
   - `target/cucumber-html-reports/index.html`

---

## Manual Test Scenarios Included

1. **Category Label Display**  
   - Display label only when categories list exists.

2. **Episode Availability Validation**  
   - Ensure version availability dates include current date.

3. **Childrens Field Validation**  
   - Check `childrens` field exists and is either true/false.
"""

readme_path = Path("/mnt/data/README.md")
readme_path.write_text(readme_content)
readme_path

