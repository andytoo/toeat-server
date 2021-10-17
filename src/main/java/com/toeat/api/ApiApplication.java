package com.toeat.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService, RestaurantService restaurantService) {
//		return args -> {
//			// ROLE
////			userService.saveRole(new Role("ROLE_USER"));
////			userService.saveRole(new Role("ROLE_MANAGER"));
////			userService.saveRole(new Role("ROLE_ADMIN"));
//
//			// USER
////			userService.saveUser(new User("John Walker", "john", "password"));
////			userService.saveUser(new User("Andy To", "andy", "password123"));
////			userService.saveUser(new User("Sherry Peng", "sherry", "123456"));
//
//			// USER ROLE
////			userService.addRoleToUser("john", "ROLE_USER");
////			userService.addRoleToUser("andy", "ROLE_ADMIN");
////			userService.addRoleToUser("sherry", "ROLE_MANAGER");
//
//			// RESTAURANT
////			restaurantService.saveRestaurant(new Restaurant("漢堡王", "Taipei", "堡王®始終抱持著提供給顧客合理的價格、高品質的產品、快速的服務以及乾淨的環境，目前為全球第二大的速食連鎖業，共超過17,000多個門市。"));
////			restaurantService.saveRestaurant(new Restaurant("麥當勞", "Taipei", "麥當勞是源自美國南加州的跨國連鎖速食店，也是世界最大的速食連鎖店，主要販售漢堡包及薯條、炸雞、碳酸飲料、冰品、沙拉、水果、咖啡等速食食品，目前總部位於美國芝加哥近郊的橡樹溪鎮。"));
////			restaurantService.saveRestaurant(new Restaurant("滷味", "Taipei", "滷味，是常見於臺灣、潮州和香港的食品，是用「滷」的烹調方法製作的食物。在台灣大部分的鄉鎮都看得到滷味攤販，甚至是店子；夜市也大都有擺設滷味攤，可說是相當常見的食物。台灣有滷味店遠至美國紐約都有開業。"));
////			restaurantService.saveRestaurant(new Restaurant("咖喱", "Taipei", "是以醬汁搭配米飯或餅的料理的統稱，通常以薑黃、芫荽、乾辣椒、丁香、肉桂、茴香、肉豆蔻、葫蘆巴、黑胡椒等香料和辣椒、薑、蒜、洋蔥等辛香料，配以肉類、海鮮、豆類、蔬菜烹煮而成，個別國家或地區的咖哩在選擇香料上受文化傳統、宗教習俗、家庭喜好等的影響，因其食材、調味、烹煮方法而有特定的名稱，常見於印度料理、泰國料理、馬來西亞料理等料理。"));
//		};
//	}
}
