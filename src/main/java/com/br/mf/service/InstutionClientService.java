package com.br.mf.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.mf.api.InstitutionClientAPI;
import com.br.mf.model.InstitutionClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
public class InstutionClientService {
	@Autowired
	InstitutionClientAPI institutionClientAPI;
	

	public String sendInstutionalClient1(String feed,String ctl) {
		 List<InstitutionClient> list = new ArrayList<>();
		 
	    // process ctl file
        List<String> columnOrder=processCtlFile(ctl);
        //process feed file
        list=  processFeedFile(feed,columnOrder);
	   // Send the list of InstitutionClient records to Kafka
	        return institutionClientAPI.sendJsonMessageToKafka(list);
		
	}
	
	// Step 2: read ctl file and get table name and store all column name in list
    public List<String> processCtlFile(String ctlFilePath) {
    	 boolean isColumnSection = false; // Flag to track if we're in the column section
         StringBuilder columnData = new StringBuilder(); 
         List<String> columnOrder = new ArrayList<>();// Collect columns across lines
        try (BufferedReader bctl = new BufferedReader(new FileReader(ctlFilePath))) {
            String line;
            while ((line = bctl.readLine()) != null) {
                line = line.trim();
                // Extract table name after "INTO TABLE"
    	        if (line.startsWith("INTO TABLE")) {
    	            String[] parts = line.split("\\s+"); // Split by any whitespace
    	            if (parts.length > 2) {
    	             //   tableName = parts[2];
    	            } else {
    	                System.out.println("Warning: Table name not found in line: " + line);
    	            }
    	        } 
    	        if (line.startsWith("(")) {
                    isColumnSection = true;
                    columnData.append(line.substring(1).trim()); // Append line without initial "("
                } 
                // Stop collecting at ")"
                else if (line.endsWith(")") && isColumnSection) {
                    columnData.append(line.substring(0, line.length() - 1).trim()); // Remove final ")"
                    break; // End of column section, so break the loop
                } 
                // Continue collecting lines within the column section
                else if (isColumnSection) {
                    columnData.append(line); // Append line as part of column data
                }
            }

            // Split column names by comma and add to list
            for (String column : columnData.toString().split(",")) {
                columnOrder.add(column.trim()); // Add trimmed column names to list
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnOrder;
        
    }
    //read file and for each every recrd call processline to get institution object
    public List<InstitutionClient> processFeedFile(String feedFilePath,List<String> columnOrder) {
    	List<InstitutionClient> list=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(feedFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	if(line.contains("%")) {
	                InstitutionClient client = processLine(line,columnOrder);
	                list.add(client);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
    }
    //create key value as json format and create instution object by passing json
    
    public InstitutionClient processLine(String line,List<String> columnOrder) throws Exception {
    	String[] values = line.split("%~\\|"); // Split by the delimiter
        ObjectMapper objectMapper =new ObjectMapper();
        // Create a dynamic JSON node with column names as keys
        ObjectNode jsonNode = objectMapper.createObjectNode();
        for (int i = 0; i < columnOrder.size(); i++) {
            jsonNode.put(columnOrder.get(i), values[i].trim());
        }
        
        // Convert JSON node to InstitutionClient instance
        return objectMapper.treeToValue(jsonNode, InstitutionClient.class);
    }

    public String sendInstutionalClient2(String feed,String ctl) {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<>();
		   // process ctl file
        List<String> columnOrder=processCtlFile(ctl);
        StringBuilder sb=new StringBuilder();
        for(String column:columnOrder) {
        	sb.append(column+""+"%");
        }
        list.add(sb.toString());
        list=processFeed(feed,columnOrder,list);
        //process feed file
		return institutionClientAPI.sendSchemaMessageToKafka(list);
	}
    public List<String> processFeed(String feedFilePath,List<String> columnOrder,List<String> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(feedFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	if(line.contains("%")) {
	               list.add(line);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
    }

    
}
