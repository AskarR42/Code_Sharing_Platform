<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
           target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#if snippet.timeRestricted>
        <span id="time_restriction">${snippet.time}</span>
        <br>
    </#if>

    <#if snippet.viewsRestricted>
        <span id="views_restriction">${snippet.views}</span>
        <br>
    </#if>

    <span id="load_date">${snippet.date}</span>
    <pre id="code_snippet"><code>${snippet.code}</code></pre>
</body>
</html>