package xspider;

import com.xspider.avro.User;

public class AvroTest {

	public static void main(String[] args) {
		User user1 = new User();
		user1.setName("Alyssa");
		user1.setFavoriteNumber(256);
		// Leave favorite color null
		System.out.println(user1);
		// Alternate constructor
		User user2 = new User("Ben", 7, "red");
		System.out.println(user2);
		// Construct via builder
		User user3 = User.newBuilder()
		             .setName("Charlie")
		             .setFavoriteColor("blue")
		             .setFavoriteNumber(null)
		             .build();
		System.out.println(user3);

	}

}
