<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Latest</title>
    <link rel="stylesheet"
           target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#list latest as snippet>
        <span id="load_date">${snippet.date}</span>
        <pre id="code_snippet"><code>${snippet.code}</code></pre>
        <hr>
    </#list>
</body>
</html>