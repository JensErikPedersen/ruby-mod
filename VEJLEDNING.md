# Rubinblok-mod: byg og test på din PC

Mod'en tilføjer rubiner til Minecraft 26.2 med Fabric:

- **Rubinmalm** og **dybskifer-rubinmalm** findes dybt under jorden fra y=-8 og ned til bunden, flest omkring y=-40. Det er dybere end guld (flest ved y=-16), men ikke så dybt som diamant (flest helt nede ved bunden). Kræver jernhakke eller bedre og taber en **rubin** (Fortune giver flere).
- **Rubin** kan også fås ved at smelte malmen i en ovn eller blast furnace.
- **Rubinblok** craftes af 9 rubiner og kan laves tilbage til 9 rubiner.
- **Rubin-værktøj og -våben:** sværd, spyd, hakke, økse, skovl og lugejern. Opskrifterne er de samme som for diamant, bare med rubiner. De er lidt svagere end diamant og stærkere end jern, men kan hakke de samme blokke som diamant (fx obsidian).
- **Rubin-rustning:** hjelm, brystplade, bukser og støvler, også med diamant-opskrifterne. Beskyttelsen ligger mellem jern og diamant.
- **Rubinspyd:** 1 rubin øverst til højre og 2 pinde skråt ned mod venstre (samme mønster som de andre spyd). Klik for at stikke, hold højreklik inde for et stormløb.
- Alle rubin-opskrifter dukker op i opskriftsbogen, når du har samlet en rubin op (malm-opskrifterne når du har samlet malm op).
- Rubin-ting kan repareres med rubiner i en ambolt og kan fortrylles.
- I kreativ tilstand: værktøj under *Værktøj og hjælpemidler*, sværd og rustning under *Kamp*.
- I kreativ tilstand: rubinen ligger under *Ingredienser*, malmene under *Naturlige blokke* og rubinblokken under *Byggeblokke*.
- Malmen dukker kun op i nye områder af verden, som ikke har været udforsket før. Lav en ny verden for at se den.

## 1. Installer værktøjerne (kun første gang)

1. **Java 25 (JDK):** Hent "Temurin 25 (LTS)" til Windows (.msi) fra https://adoptium.net og installer den. Sæt flueben ved "Set JAVA_HOME".
2. **IntelliJ IDEA Community** (gratis) fra https://www.jetbrains.com/idea/download. Det skal være version 2025.3 eller nyere.
3. **Fabric Loader til spillet:** Hent installeren fra https://fabricmc.net/use/installer, kør den, vælg fanen *Client*, Minecraft-version **26.2** og tryk *Install*. Det giver en ny "fabric-loader-26.2" profil i Minecraft Launcher.
4. **Fabric API til spillet:** Hent filen `fabric-api-0.161.0+26.2.jar` fra https://modrinth.com/mod/fabric-api (vælg version til 26.2) og læg den i
   `C:\Users\jense\AppData\Roaming\.minecraft\mods`
   (opret mappen `mods`, hvis den ikke findes).

## 2. Åbn projektet

1. Kopiér mappen `ruby-mod` til din PC, fx `C:\Users\jense\Documents\ruby-mod`.
2. Start IntelliJ, vælg **Open** og vælg mappen `ruby-mod`.
3. IntelliJ opdager, at det er et Gradle-projekt, og begynder at hente Minecraft og Fabric. Første gang tager det nogle minutter.
4. Hvis IntelliJ klager over Java: gå til *File → Settings → Build, Execution, Deployment → Build Tools → Gradle* og sæt **Gradle JVM** til Java 25.

## 3. Test hurtigt fra IntelliJ (anbefalet)

1. Åbn Gradle-panelet i højre side.
2. Dobbeltklik på **Tasks → fabric → runClient**.
3. Minecraft starter med din mod. Lav en ny verden i kreativ tilstand og kig i fanen *Byggeblokke*.

## 4. Byg en .jar og spil med den i det rigtige spil

1. Åbn terminalen nederst i IntelliJ og skriv:
   ```
   .\gradlew build
   ```
2. Den færdige fil ligger i `ruby-mod\build\libs\rubymod-1.3.1.jar`. (Ignorer filen, der ender på `-sources.jar`, hvis den er der.)
3. Kopiér `rubymod-1.3.1.jar` til `C:\Users\jense\AppData\Roaming\.minecraft\mods`.
4. Start Minecraft Launcher, vælg profilen **fabric-loader-26.2** og tryk Play.

## Hvis noget går galt

- **"Unsupported class file major version" eller Java-fejl:** Gradle bruger en forkert Java. Se trin 2.4.
- **Blokken er lilla og sort ternet:** Teksturen blev ikke fundet. Tjek at `ruby_block.png` ligger i `src/main/resources/assets/rubymod/textures/block/`.
- **Spillet crasher ved start:** Kopiér den røde fejltekst fra IntelliJ eller filen `.minecraft\logs\latest.log` og send den til mig.

## Hvor er hvad i koden

| Fil | Hvad den gør |
|---|---|
| `src/main/java/dk/jens/rubymod/RubyMod.java` | Startpunktet. Fabric kalder den, når spillet starter. |
| `src/main/java/dk/jens/rubymod/ModBlocks.java` | Opretter rubinblokken (hårdhed, lyd, farve) og lægger den i kreativ-menuen. |
| `assets/rubymod/textures/block/ruby_block.png` | Billedet på blokken (16×16 pixels). Du kan tegne dit eget. |
| `assets/rubymod/lang/da_dk.json` og `en_us.json` | Blokkens navn på dansk og engelsk. |
| `src/main/java/dk/jens/rubymod/ModItems.java` | Opretter rubinen, værktøjet og rustningen. Her kan du ændre holdbarhed, skade og beskyttelse. |
| `assets/rubymod/textures/item/` | Billederne af rubinen, værktøjet og rustningen i inventaret. |
| `assets/rubymod/textures/entity/equipment/` | Hvordan rustningen ser ud, når man har den på. |
| `data/rubymod/recipe/` | Opskrifterne (crafting, ovn og blast furnace). |
| `data/rubymod/worldgen/` | Hvor ofte og hvor dybt rubinmalmen findes. Ret `count` i `placed_feature/ruby_ore.json` for at få mere eller mindre malm. |
| `data/rubymod/loot_table/blocks/` | Hvad blokkene taber, når de bliver brudt. |
| `gradle.properties` | Versionsnumre for Minecraft, Fabric og din mod. |
