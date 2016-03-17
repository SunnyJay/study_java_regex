package testredis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
public class TestRedis {

	 private Jedis jedis; 
	 
	public static void main(String[] args)
	{
		TestRedis test = new TestRedis();
		
		test.testConnect();
		test.testString();
		test.testSet();
		test.testList();
		test.testMap();
		test.testCommand();
	}
	
	public void testConnect() {
		
		System.out.println("************************testConnect************************");
		jedis = new Jedis("10.1.1.90", 6379);
		
	}
	
	public void testCommand(){
		System.out.println("************************testCommand************************");
		System.out.println("^^^^^^^^info^^^^^^^^^^");
		System.out.println(jedis.info());
		System.out.println("^^^^^^^^keys*^^^^^^^^^^");
		System.out.println(jedis.keys("*"));
		System.out.println("^^^^^^^^dbsize^^^^^^^^^^");
		System.out.println(jedis.dbSize());
		System.out.println("^^^^^^^^ping^^^^^^^^^^");
		System.out.println(jedis.ping());

		
	}
	
    public void testString() {
        
    	System.out.println("************************testString************************");
    	jedis.flushDB();
    	
        jedis.set("name","jack");
        System.out.println(jedis.get("name")); 
        
        jedis.append("name", " sun");
        System.out.println(jedis.get("name")); 
        
        jedis.del("name");
        System.out.println(jedis.get("name"));
        
        
        jedis.mset("name","tom","age","23","qq","123456789");
        jedis.incr("age"); 
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq")); 
    }
	
    public void testMap() {
       
    	System.out.println("************************testMap************************");
    	jedis.flushDB();
    	
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "wu");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        

        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);  
  
        
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); 
        System.out.println(jedis.hlen("user")); 
        System.out.println(jedis.exists("user"));
        System.out.println(jedis.hkeys("user"));
        System.out.println(jedis.hvals("user"));
  
        Iterator<String> iter=jedis.hkeys("user").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println(key+":"+jedis.hmget("user",key));  
        }  
    }

    public void testSet(){  
        
    	System.out.println("************************testSet************************");
    	jedis.flushDB();
        jedis.sadd("user","zhao");  
        jedis.sadd("user","qian");  
        jedis.sadd("user","sun");  
        jedis.sadd("user","li");
        jedis.sadd("user","zhou");  
       
        jedis.srem("user","who");  
        System.out.println(jedis.smembers("user"));
        System.out.println(jedis.sismember("user", "who"));
        System.out.println(jedis.srandmember("user"));  
        System.out.println(jedis.scard("user"));
        
        jedis.sadd("user2","zhao");  
        jedis.sadd("user2","sun");  
        jedis.sadd("user2","zhou");  
        jedis.sadd("user2","li");
        jedis.sadd("user2","wu"); 
        
        System.out.println(jedis.sinter("user","user2"));
        System.out.println(jedis.sunion("user","user2"));
        System.out.println(jedis.sdiff("user","user2"));
    }  
  
    
  
    public void testList(){  
       
    	System.out.println("************************testList************************");
    	jedis.flushDB();
        jedis.lpush("study","java");  
        jedis.lpush("study","c++");  
        jedis.lpush("study","c");  
        
        System.out.println(jedis.lrange("study",0,-1));  
        
        jedis.del("study");
        jedis.rpush("study","java");  
        jedis.rpush("study","c");  
        jedis.rpush("study","c++"); 
        System.out.println(jedis.lrange("study",0,-1));
    }  
    
}
