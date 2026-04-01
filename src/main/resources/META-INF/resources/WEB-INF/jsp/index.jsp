<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>PMAY | Full Integration Hub</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600;700&family=JetBrains+Mono&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary: #4f46e5;
            --bg-light: #f1f5f9;
            --sidebar-bg: #0f172a;
            --text-main: #1e293b;
            --text-muted: #64748b;
            --accent: #818cf8;
        }

        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Outfit', sans-serif; }

        body { display: flex; height: 100vh; background: var(--bg-light); color: var(--text-main); }

        .sidebar {
            width: 320px; background: var(--sidebar-bg); color: white; padding: 1.5rem;
            display: flex; flex-direction: column; border-right: 1px solid rgba(255,255,255,0.1);
        }

        .logo-box { padding-bottom: 1.5rem; margin-bottom: 1rem; border-bottom: 1px solid rgba(255,255,255,0.1); }
        .logo { font-size: 1.5rem; font-weight: 700; color: var(--accent); }

        .sidebar-list { flex: 1; overflow-y: auto; }
        .sidebar-group { margin-top: 1.5rem; font-size: 0.7rem; color: #475569; text-transform: uppercase; font-weight: 700; }

        .endpoint-btn {
            padding: 0.75rem 1rem; margin-top: 0.25rem; border-radius: 8px; cursor: pointer; transition: 0.2s;
            font-size: 0.85rem; color: #94a3b8;
        }
        .endpoint-btn:hover { background: rgba(255,255,255,0.05); color: white; }
        .endpoint-btn.active { background: var(--primary); color: white; }

        .main { flex: 1; padding: 2.5rem; overflow-y: auto; display: flex; flex-direction: column; }
        .header { margin-bottom: 2rem; }
        .grid { display: grid; grid-template-columns: 1fr 1.2fr; gap: 2rem; flex: 1; }

        .card { background: white; border-radius: 20px; border: 1px solid #e2e8f0; padding: 1.5rem; display: flex; flex-direction: column; }
        textarea { width: 100%; flex: 1; padding: 1rem; border-radius: 12px; border: 1px solid #e2e8f0; background: #f8fafc; font-family: 'JetBrains Mono', monospace; font-size: 0.85rem; resize: none; outline: none;}

        .response-container { background: #0f172a; border-radius: 12px; flex: 1; overflow: auto; padding: 1rem; }
        .response-box { color: #38bdf8; font-family: 'JetBrains Mono', monospace; font-size: 0.85rem; line-height: 1.5;}

        .btn-execute { background: var(--primary); color: white; border: none; padding: 0.75rem 2rem; border-radius: 8px; font-weight: 600; cursor: pointer; float: right; margin-bottom: 1rem;}
    </style>
</head>
<body>

<div class="sidebar">
    <div class="logo-box"><div class="logo">PMAY Hub</div></div>
    <div class="sidebar-list" id="endpointList"></div>
</div>

<div class="main">
    <div class="header">
        <h1 id="activeName">Select an API</h1>
        <p id="activeUrl">POST /api/v1/...</p>
    </div>
    <div class="grid">
        <div class="card">
            <button class="btn-execute" onclick="send()">RUN API</button>
            <textarea id="payload" spellcheck="false"></textarea>
        </div>
        <div class="card">
            <div class="response-container">
                <pre class="response-box" id="resBox">// Results will appear here...</pre>
            </div>
        </div>
    </div>
</div>

<script>
    const endpoints = [
        { group: 'State', items: [
            { name: 'Get State', url: '/api/v1/getState', type: 'JSON', body: { slsCode: 'UP' } },
            { name: 'SNA Details', url: '/api/v1/getSNADetails', type: 'JSON', body: { slsCode: 'UP', finYear: '2023-24' } }
        ]},
        { group: 'Finance', items: [
            { name: 'Get Deduction', url: '/api/v1/getDeduction', type: 'JSON', body: { slsCode: 'UP' } },
            { name: 'Mother Sanction', url: '/api/v1/motherSanctionLimit', type: 'JSON', body: { slsCode: 'UP' } }
        ]}
    ];

    let current = endpoints[0].items[0];

    function init() {
        const list = document.getElementById('endpointList');
        endpoints.forEach(group => {
            const g = document.createElement('div');
            g.className = 'sidebar-group'; g.innerText = group.group;
            list.appendChild(g);
            group.items.forEach(i => {
                const b = document.createElement('div');
                b.className = 'endpoint-btn'; b.innerText = i.name;
                b.onclick = () => select(i, b);
                list.appendChild(b);
                if(i === current) select(i, b);
            });
        });
    }

    function select(item, btn) {
        document.querySelectorAll('.endpoint-btn').forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        current = item;
        document.getElementById('activeName').innerText = item.name;
        document.getElementById('activeUrl').innerText = "POST " + item.url;
        document.getElementById('payload').value = JSON.stringify(item.body, null, 2);
    }

    async function send() {
        const body = document.getElementById('payload').value;
        const box = document.getElementById('resBox');
        box.innerText = "Processing...";
        try {
            const resp = await fetch(current.url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json', 'Authorization': 'Basic ' + btoa('ifmisuser:ifmispassword') },
                body: body
            });
            const text = await resp.text();
            try { box.innerText = JSON.stringify(JSON.parse(text), null, 2); }
            catch { box.innerText = text; }
        } catch (e) { box.innerText = "Error: " + e.message; }
    }
    init();
</script>
</body>
</html>
