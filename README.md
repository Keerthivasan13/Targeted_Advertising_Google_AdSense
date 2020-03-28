# MagicAds - Targeted Advertising System
This project aims to do **E-marketing** by connecting the **Business Advertisers** with the **Public** (customers) by displaying **Relevant and Preferred Online Advertisements** via Websites called **Publishers**, resulting in Website Monetization just like Google AdSense.

## Software and Languages ##
**Languages -** Java, Java Servet Pages (JSP), JavaScript  
**Libraries -** Weka, JSoup, Apache POI  
**Database -** MySQL  
**Server -** Tomcat 7.0.56  

## Functionalities ##
**Idea -** Platform where Advertisers sells Advertisements and Publisher Websites gets paid for displaying those Ads  
**Algorithms used -** Naive Bayes (For Text Classification) and Ad Ranking Algorithm (Based on 5 features)  
**Monetization Factors -** Cost per Click (CPC), Cost per Impression (CPM)  
**Features -** Relevancy of Ad to the Page, Location, CPC, CPM, Premium Membership  

## Implementation ##
### 1. Natural Language Processing - Information Retrieval 
**1. Webpage Keyword Extractor -** Designed a Multi-threaded **Crawler** to parse and extract the keywords from each webpage's Metatag using JSoup  
**2. Keyword Splitter -** The words and phrases were split into unique tokens  
**3. Stop-Words Filter -** Blacklisted the Stop-Words that were present within the Keywords  
**4. Delimiter -** Set of Keywords were delimited by space using String Tokenizer. **Apache POI** was used to read and write data to an Excel sheet  
**5. Database Insertion -** Keywords from the Workbook (Excel file) were inserted into Database using Java DataBase Connectivity **(JDBC)**   

### 2. Data Mining - Naive Bayes Classifier
1. Weka Library is used to perform classification of the Advertisement and Webpage category and produce AdKeyword and PageKeyword models.  
2. When the Advertiser or Webpage registers with MagicAds, the Keywords are extracted and the corresponding class is predicted.  
3. **Ad-Page Mapping Table** contains the mapping to determine what Ads to display in each Webpage.  

### 3. Ranking Algorithm 
1. When the user visits the Publisher's page, his IP Address is obtained and location is found out by **Geo-location** library.  
2. The System **filters the Relevant Ads** based on location and uses the following formula.  
**Min (CPC / Clicks + CPM / Impressions) * Cost_Factor** where Cost_Factor = 1 for Non-Premium Advertisers and 1.5 for Premium Advertisers.  
3. System targets to fetch **2 x No. of ads supported by the webpage** and changes advertisements every **5 secs**.
4. If the Ads fetched is less than this number, then the Global Advertisements are also included to make the count greater.
5. Finally the ads are displayed and the Impressions count is incremented in the database.
6. Similarly if an user clicks the ad the Clicks count is incremented in the database.
7. Hence the same ads are not displayed every time and **Fairness** is maintained among ads.

## Architecture
![Architecture](/System_Architecture.jpg)

### Summary
1. The webpage data will be collected by the **CRAWLER**. 
2. The webpage content is mapped to a category from a predefined list of categories by the **DATA MINING ENGINE** (Classifier). 
3. The same is done for data collected from the Advertiser who wants to advertise their products.
4. Their products are also classified based on the keywords.
5. The website user or the public who sees the webpage will be asked to give his IP address to the website. The website passes that information to our engine. The engine gets the geo-location of the user from his IP.
6. A **RANKER** engine is used to rank the advertisements based on the ranking algorithm created by us. Location and the keyword mapping is done by the engine.
7. The webpage category can display certain advertisement categories and this mapping is the Keyword mapping.
8. Finally using JavaScript the top Advertisements are displayed on the webpage. Their viewcount and clicks count will be updated in the database

### In Action
![Output](/Output.jpg)


