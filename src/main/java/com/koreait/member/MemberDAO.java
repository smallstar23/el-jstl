package com.koreait.member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.koreait.db.DBconn;

public class MemberDAO{

		Connection conn;
	   PreparedStatement pstmt;
	   ResultSet rs;
	   String sql = "";

public int join(MemberDTO member) {


	try {
		conn= DBconn.getConnection();
		sql = "insert into tb_member(mem_userid, mem_userpw, mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getUserid());
        pstmt.setString(2, member.getUserpw());
        pstmt.setString(3, member.getName());
        pstmt.setString(4, member.getHp());
        pstmt.setString(5, member.getEmail());
        String hobbystr="";
    	for(String str: member.getHobby()) {
    		hobbystr += str+ " ";
    	};
        pstmt.setString(6, hobbystr);
        pstmt.setString(7, member.getSsn1());
        pstmt.setString(8, member.getSsn2());
        pstmt.setString(9, member.getZipcode());
        pstmt.setString(10, member.getAddress1());
        pstmt.setString(11, member.getAddress2());
        pstmt.setString(12, member.getAddress3());
     
        if(pstmt.executeUpdate()>0) {
        	return 1;
        };
	}catch(Exception e){
		e.printStackTrace();
	}
	return 0;
}

public MemberDTO login(MemberDTO member) {
	try {
		conn= DBconn.getConnection();
	      sql ="select mem_userid, mem_idx, mem_name from tb_member where mem_userid=? and mem_userpw=?";
	      pstmt= conn.prepareStatement(sql);
	        pstmt.setString(1, member.getUserid());
	        pstmt.setString(2, member.getUserpw());
	      rs= pstmt.executeQuery();
	      if(rs.next()){
	    	  member.setIdx(rs.getInt("mem_idx"));
	    	  member.setUserid(rs.getString("mem_userid"));
	    	  member.setName(rs.getString("mem_name"));
	    	  return member;

}else {
	return null;
}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}


public MemberDTO info(MemberDTO member) {
	
	try{
		   conn = DBconn.getConnection();

		   if(conn != null){
			   
		      sql ="select mem_name, mem_hp, mem_email, mem_hobby, mem_ssn1, mem_ssn2, mem_zipcode, mem_address1, mem_address2, mem_address3 from tb_member where mem_idx=?";
		      pstmt= conn.prepareStatement(sql);
		      pstmt.setInt(1, member.getIdx());
		      rs= pstmt.executeQuery();
		      
		      if(rs.next()){
		    	  member.setName(rs.getString("mem_name"));
		    	  member.setHp(rs.getString("mem_hp"));
		    	  member.setEmail(rs.getString("mem_email"));
		    	  member.setHobbystr(rs.getString("mem_hobby"));
		    	  member.setSsn1(rs.getString("mem_ssn1"));
		    	  member.setSsn2(rs.getString("mem_ssn2"));
		    	  member.setZipcode(rs.getString("mem_zipcode"));
		    	  member.setAddress1(rs.getString("mem_address1"));
		    	  member.setAddress2(rs.getString("mem_address2"));
		    	  member.setAddress3(rs.getString("mem_address3"));
		    	  return member;
		      }
		      
		   }
		}catch(Exception e){
		   e.printStackTrace();
		}
	return null;
}

public MemberDTO edit(MemberDTO member) {
	
	try {
		conn= DBconn.getConnection();
        sql = "update tb_member set mem_name=?, mem_hp=?, mem_email=?, mem_hobby=?, mem_zipcode=?, mem_address1=?, mem_address2=?, mem_address3=? where mem_idx=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getName());
        pstmt.setString(2, member.getHp());
        pstmt.setString(3, member.getEmail());
        String hobbystr="";
    	for(String str: member.getHobby()) {
    		hobbystr += str+ " ";
    	};
        pstmt.setString(4, hobbystr);
        pstmt.setString(5, member.getZipcode());
        pstmt.setString(6, member.getAddress1());
        pstmt.setString(7, member.getAddress2());
        pstmt.setString(8, member.getAddress3());
        pstmt.setInt(9, member.getIdx());
        pstmt.executeUpdate();
        return member;
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return null;
}


public int totalCount() {
	int totalCount;
	try {conn= DBconn.getConnection();
		if(conn != null){
            sql = "select count(b_idx) as total from tb_board";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
               totalCount = rs.getInt("total");
               return totalCount;
            }
            
		}

	}catch(Exception e) {
		e.printStackTrace();
	}
	return 0;
	
	}

public String[] list() {
	try {
		conn= DBconn.getConnection();
		if(conn != null){
			sql= "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_content from tb_board order by b_idx desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            String[] result=new String[6];
            
            while(rs.next()) {
                String b_idx = rs.getString("b_idx");
                String b_title = rs.getString("b_title");
                String b_content = rs.getString("b_content");
                String b_regdate = rs.getString("b_regdate").substring(0,10);
                String b_hit=String.valueOf(rs.getInt("b_hit"));
                String b_like = rs.getString("b_like");
                result[0]=b_idx;
                result[1]=b_title;
                result[2]=b_content;
                result[3]=b_regdate;
                result[4]=b_hit;
                result[5]=b_like;
                
            }
            return result;
            
            
		}

	}catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}


}
