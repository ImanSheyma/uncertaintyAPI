# uncertaintyAPI
UncertaintyAPI is a API for economic uncertainty index of Belarus

# metodology
All uncertainty indexes used in this project are based on bussines surveys.
Uncertainty indexes are calculated as variances between the proportions of survey responses and normalized so that their mean is 100 and the standard deviation is 10

# Response
In response to requests, the API provides an array of json objects, which is a time series of Unceratinty. 
  
  json object: {"date" : date , "value" : value}
