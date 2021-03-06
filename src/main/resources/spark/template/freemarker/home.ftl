<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <#if num_of_players??>
      <div id="num_of_players" class="${num_of_players.type}">${num_of_players.text}</div>
    </#if>

    <#if available_players??>
      <br>
        Available Players
      </br>
      <#list available_players as n>
        <form action="./game" method="POST" id="opposite">
          <input type="hidden" value="${n}">
          <button type="submit" form="opposite" name="opposite" value="${n}">${n}</button>
        </form>
      </#list>
    </#if>

    <#if current_games??>
          <br>
            Current Games
          </br>
          <#list current_games as p>
            <form action="./game" method="GET" id="game_id">
              <input type="hidden" value="${p.getId()}">
              <button type="submit" form="game_id" name="game_id" value="${p.getId()}">${p}</button>
            </form>
          </#list>
    </#if>

    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
