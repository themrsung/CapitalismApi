# CapitalismApi v5.0

### Supports: Capitalism v5.1.4 or higher

This is an open source API for the Minecraft plugin **Capitalism** developed by themrsung.
Use this API to access data in the main plugin from other modules.

**Capitalism** is currently closed source and not for sale.
You may use this to develop plugins for the author's Minecraft server "자본주의 서버".

#### Server information:

- Adderss: jabonmc.mcok.kr
- Version: 1.20 (PaperMC)

Code is commented in both English and Korean.

#### What this API can do

You can access **Capitalism**s data, which is updated every 50 ticks (2.5s).
If there is a change in the APIs state, it will affect other plugins that utilize this API, but not **Capitalism** itself.

#### How to use this API

Access the plugin's data by calling **Bukkit.getPluginManager().getPlugin("CapitalismApi")**.
Creating a new instance and using save/load is very inefficient, and is not recommended.

Add **Capitalism** and **CapitalismApi** as dependencies to your **plugin.yml**.

#### Can't I just get the main plugin's instance?

This causes performance issues.

For example, let's say you are trying to get the stock price of a certain stock to display it on the user's scoreboard.
If you use the main plugin instance, you could get the latest value, but the plugin will loop through all stock exchanges, then loop through all stock listings to find the highest price.
This will be very laggy, for obvioud reasons.

However, this can be overcome by using the APIs cached value. Moreover, **Capitalism**'s source code is not public.
