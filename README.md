# NeckFlex
## About Project
A desktop media renting platform
### Rules
**Renting Rule**

 User will get awarded with credit points when they return their items on time. 
 The more points accumulated, the higher chance a user will be upgraded to higher status.

**Account Priviledge**
| Account type | Features |
| :--: | :-- |
| Guest | - Maximum amount for renting: 2 items. <br> - Available renting duration: 2 days. |
| Regular | - Unlimited amount of renting items. <br> - Can rent items up to 1 week. |
| Vip | - Can checkout with reward points, with the cost of 10 points/item. |

**Others**

When an admin delete an item, it is still recorded in user's order list.
However, when a user return an item that is already deleted by admin, it will not be restored to be shown on store

## Technology Stack
- Frontend: JavaFX, CSS
- Backend: Text files with MVC Model
- Project Manager: Maven

### Dependencies
| Dependency | Version |
| :-- | :--: |
| org.openjfx:javafx-controls | 19 |
| org.openjfx:javafx-fxml | 19 |
| org.junit.jupiter:junit-jupiter-api | 5.9.1 |
| org.junit.jupiter:junit-jupiter-engine | 5.9.1 |

### Plugins
| Plugin | Version |
| :-- | :--: |
| org.apache.maven.plugins:maven-compiler-plugin | 3.10.1 |
| org.openjfx:javafx-maven-plugin | 19 |

## Others
### Account samples

| Account type | Username | Password 
| -- | :--: | :--: |
| Admin | joun123 | 123 |
| Guest | steven101 | 101 |
| Regular | david469 | 469 |
| VIP | jasmine334 | 334 |

A list of extra accounts can be found in `src/main/resources/com/groupproject/media/text/account.txt`

### Backup save files 
There are 2 types of save files - text & images
- Project save files location: `src/main/resources/com/groupproject/media/`
- Backup save files location: `doc/backupData/` 

## Show cases
![alt text for screen readers](/img1 "Text to show on mouseover")

![alt text for screen readers](/img2 "Text to show on mouseover")

![alt text for screen readers](/img3 "Text to show on mouseover")


## Contributors
<a href="https://github.com/OnlyUsePascal/NeckFlex/graphs/contributors">
  <img width="64" height="64" style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/62138004?v=4" alt="">
  <img width="64" height="64" style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/17351940?v=4" alt="">
  <img width="64" height="64" style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/68006452?v=4" alt="">
  <img width="64" height="64" style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/100990206?v=4" alt="">
</a>
 
