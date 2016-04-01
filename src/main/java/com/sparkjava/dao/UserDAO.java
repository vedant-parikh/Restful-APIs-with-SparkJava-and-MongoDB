package com.sparkjava.dao;

import com.sparkjava.vo.AddressVO;
import com.sparkjava.vo.CompanyVO;
import com.sparkjava.vo.UserVO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class UserDAO {
	MongoCollection<Document> userCollection = new DBHelper().getDatabase().getCollection("user");

	public boolean createUser(UserVO user) {
		if (!this.isExistingUser(user)) {
			Document userDoc = new Document();
			userDoc.put("_id", user.getId());
			userDoc.put("first_name", user.getFirstName());
			userDoc.put("last_name", user.getLastName());
			userDoc.put("email", user.getEmail());
			userDoc.put("created_date", user.getDateCreated());
			userDoc.put("profile_pic", user.getProfilePic());
			
			Document addressDoc = new Document();
			addressDoc.put("street", user.getAddress().getStreet());
			addressDoc.put("state", user.getAddress().getState());
			addressDoc.put("city", user.getAddress().getCity());
			addressDoc.put("zipcode", user.getAddress().getZip());
			addressDoc.put("state", user.getAddress().getState());
			addressDoc.put("country", user.getAddress().getCountry());
			
			Document companyDoc = new Document();
			companyDoc.put("name", user.getCompany().getName());
			companyDoc.put("website", user.getCompany().getWebsite());
			
			userDoc.put("address", addressDoc);
			userDoc.put("company", companyDoc);
			
			userCollection.insertOne(userDoc);
			return true;
		} else {
			return false;
		}
	}

	public boolean isExistingUser(UserVO user) {
		long userCount = userCollection.count(eq("_id", user.getId()));
		if (userCount>0) {
			return true;
		}
		return false;
	}

	public List<UserVO> getAllUsers() {
		List<UserVO> users = new ArrayList<>();
		FindIterable<Document> userDocs = userCollection.find();
		try (MongoCursor<Document> userCursor = userDocs.iterator()) {
		    while (userCursor.hasNext()) {
		    	Document userDoc = userCursor.next();
		    	UserVO user = new UserVO();
		    	user.setId(userDoc.get("_id").toString());
		    	user.setFirstName(userDoc.get("first_name").toString());
		    	user.setLastName(userDoc.get("last_name").toString());
		    	user.setEmail(userDoc.get("email").toString());
		    	user.setDateCreated(userDoc.get("created_date").toString());
		    	user.setProfilePic(userDoc.get("profile_pic").toString());
		    	
		    	Document addressDoc = (Document)userDoc.get("address");
		    	AddressVO address = new AddressVO();
		    	address.setStreet(addressDoc.getString("street").toString());
		    	address.setCity(addressDoc.getString("city").toString());
		    	address.setState(addressDoc.getString("state").toString());
		    	address.setCountry(addressDoc.getString("country").toString());
		    	address.setZip(addressDoc.getString("zipcode").toString());
		    	
		    	Document companyDoc = (Document)userDoc.get("company");
		    	CompanyVO company = new CompanyVO();
		    	company.setName(companyDoc.getString("name").toString());
		    	company.setWebsite(companyDoc.getString("website").toString());
		    	
		    	user.setAddress(address);
		    	user.setCompany(company);
		    	
		        users.add(user);
		    }
		}
		return users;
	}

	public boolean updateUser(UserVO user) {
		if (this.isExistingUser(user)) {
			Document userDoc = new Document();
			userDoc.put("first_name", user.getFirstName());
			userDoc.put("last_name", user.getLastName());
			userDoc.put("email", user.getEmail());
			userDoc.put("created_date", user.getDateCreated());
			userDoc.put("profile_pic", user.getProfilePic());
			
			Document addressDoc = new Document();
			addressDoc.put("street", user.getAddress().getStreet());
			addressDoc.put("state", user.getAddress().getState());
			addressDoc.put("city", user.getAddress().getCity());
			addressDoc.put("zipcode", user.getAddress().getZip());
			addressDoc.put("state", user.getAddress().getState());
			addressDoc.put("country", user.getAddress().getCountry());
			
			Document companyDoc = new Document();
			companyDoc.put("name", user.getCompany().getName());
			companyDoc.put("website", user.getCompany().getWebsite());
			
			userDoc.put("address", addressDoc);
			userDoc.put("company", companyDoc);
			userCollection.updateOne(eq("_id", user.getId()), new Document("$set",userDoc));
			return true;
		} else {
			return false;
		}
	}
}
