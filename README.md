# ShinyCounter
A simple counter to keep track of how many Pokemon you've seen before finding a Shiny.

- Option to add **+1**, **+3**, or **+5** Pokemon at once if you're using Sweet Scent to summon hordes.
- Default settings: **1** in **8192** Pokemon is Shiny. You can change this in the Options.
- Option to Reset or Change the counter.
- The program shows what the chance was to see at least one Shiny in the number of attempts you did. This is `1-(1-shiny_chance)^attempts` (Geometric distribution), for example if the chance to see a shiny is **1** in **8192**, and you've made **5000** attempts to see a shiny, the chance for you to have encountered at least **1** Shiny is `1-(1-1/8192)^5000 ≈ 45.68 %`.


To **download** the executable, simply download the **ShinyCounter folder**. If you're interested in the source code, check the src folder.

Author: **Atricos**<br>
Programming Language: **Java, JFrame**<br>
Uploaded: **October 11th, 2020**
