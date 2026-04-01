<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>PMAY | API Dashboard</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600;700&family=JetBrains+Mono&display=swap" rel="stylesheet">
    <style>
        :root { --primary: #4f46e5; --bg: #f8fafc; --sidebar: #0f172a; }
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Outfit', sans-serif; }
        body { display: flex; height: 100vh; background: var(--bg); }
        .sidebar { width: 300px; background: var(--sidebar); color: white; padding: 1.5rem; display: flex; flex-direction: column; }
        .logo { font-size: 1.25rem; font-weight: 700; color: #818cf8; margin-bottom: 2rem; }
        .item { padding: 0.75rem 1rem; border-radius: 8px; cursor: pointer; color: #94a3b8; font-size: 0.85rem; }
        .item:hover { background: rgba(255,255,255,0.05); color: white; }
        .main { flex: 1; padding: 2.5rem; overflow-y: auto; }
        .grid { display: grid; grid-template-columns: 1fr 1fr; gap: 2rem; }
        textarea { width: 100%; height: 350px; padding: 1rem; border-radius: 12px; border: 1px solid #e2e8f0; font-family: 'JetBrains Mono', monospace; font-size: 0.85rem; }
        pre { background: #0f172a; color: #38bdf8; padding: 1rem; border-radius: 12px; height: 350px; overflow: auto; font-family: 'JetBrains Mono', monospace; font-size: 0.85rem; }
        .btn { background: var(--primary); color: white; border: none; padding: 0.75rem 2rem; border-radius: 8px; font-weight: 600; cursor: pointer; margin-bottom: 1rem; }
    </style>
</head>
<body>
<div class="sidebar">
    <div class="logo">PMAY Integration</div>
    <div onclick="test('/api/v1/getState')" class="item">Get State</div>
    <div onclick="test('/api/v1/getSNADetails')" class="item">SNA Details</div>
    <div onclick="test('/api/v1/getDeduction')" class="item">Get Deduction</div>
</div>
<div class="main">
    <h1 id="title">API Dashboard</h1>
    <div class="grid">
        <div>
            <button class="btn" onclick="run()">EXECUTE</button>
            <textarea id="in">{"slsCode": "UP"}</textarea>
        </div>
        <div>
            <pre id="out">// Response...</pre>
        </div>
    </div>
</div>
<script>
    let url = '/api/v1/getState';
    function test(u) { url = u; document.getElementById('title').innerText = "Testing " + u; }
    async function run() {
       const box = document.getElementById('out'); box.innerText = "Calling...";
       const resp = await fetch(url, { method:'POST', headers:{'Content-Type':'application/json','Authorization':'Basic '+btoa('ifmisuser:ifmispassword')}, body:document.getElementById('in').value });
       const text = await resp.text();
       try { box.innerText = JSON.stringify(JSON.parse(text), null, 2); } catch { box.innerText = text; }
    }
</script>
</body>
</html>
