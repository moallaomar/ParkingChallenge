# ParkingChallenge

Cher(e) Tech lead, 

Tout d'abord, je tiens à vous remercier sincèrement d'avoir pris le temps de lire mon code. Votre intérêt et votre attention sont grandement appréciés. Je voulais également vous informer que la réalisation de ce projet m'a demandé un total de 4 heures et 45 minutes, incluant la réflexion, la mise en œuvre et la rédaction de ce document.

Passons aux choses sérieuses... pour vous expliquer brièvement la solution que j'ai mise en place, j'ai commencé par créer deux tables qui permettent de stocker l'URI et une liste d'attributs à récupérer lors de l'éxploitation de la réponse au format JSON, ainsi que les propriétés de mon DTO (Parking). Ensuite, j'ai commencé à développer le service. 

Comme mentionné, l'application doit être capable de gérer une ou plusieurs villes. À partir des coordonnées, j'ai utilisé OpenStreetMap comme référentiel pour effectuer un reverse geo coding afin d'obtenir le nom de la ville. Une fois cette opération réalisée, nous disposons de la ville, des coordonnées et des données à récupérer au moment du parsing. Pour setter les valeurs récupérées du JSON dans les propriétés de l'objet, je me suis appuyé sur le concept de "reflection" de Java. 


L'application fonctionne avec une in-memory DB de type H2, qui est alimentée au démarrage de l'application via l'interface Command Line Runner. Vous pouvez tester l'API dans deux villes : Poitiers et Lille via un :  

GET : http://localhost:8080/parkings?longitude=0.345002261647649&latitude=46.58349874703973 

Si vous souhaitez également tester l'application à l'aide des tests unitaires et des tests d'intégration, ils sont également disponibles. Je tiens à préciser que je suis parti du principe que les URI comportent uniquement des query params et non des path params. 


Malheureusement, je ne suis pas fier de la partie où j'alimente la HashMap, en particulier la ligne 130 de la classe ParkingServiceImpl, qui fait le lien entre la première source de données et la deuxième.

Pour notre exemple (Poitiers), ce sont les places libres qui diffèrent, mais pour une autre ville, ce pourrait être la capacité.

Encore une fois, je vous remercie de l'attention que vous avez portée à mon code et de l'intérêt que vous y avez manifesté. N'hésitez pas à me faire part de vos commentaires ou de toute question supplémentaire que vous pourriez avoir.

Cordialement,
Omar
