<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>PMAY | Master Enterprise Portal</title>
    <!-- Google Fonts & Chart.js -->
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600;700&family=JetBrains+Mono&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        :root { --primary: #4f46e5; --bg: #f8fafc; --sidebar: #0f172a; --accent: #818cf8; }
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Outfit', sans-serif; }
        body { display: flex; height: 100vh; background: var(--bg); color: #1e293b; overflow: hidden; }

        /* Sidebar Decor */
        .sidebar { width: 300px; background: var(--sidebar); color: white; padding: 1.5rem; display: flex; flex-direction: column; }
        .logo { font-size: 1.5rem; font-weight: 700; color: var(--accent); margin-bottom: 2.5rem; letter-spacing: -1.5px; }
        
        .nav-item { 
            padding: 0.85rem 1rem; margin-bottom: 0.5rem; border-radius: 12px; cursor: pointer; transition: 0.2s;
            font-size: 0.9rem; color: #94a3b8; display: flex; align-items: center; gap: 12px;
        }
        .nav-item:hover { color: white; background: rgba(255,255,255,0.05); }
        .nav-item.active { background: var(--primary); color: white; box-shadow: 0 4px 12px rgba(79, 70, 229, 0.4); }

        /* Main Context */
        .main { flex: 1; padding: 2.5rem; overflow-y: auto; display: flex; flex-direction: column; }
        .view-section { display: none; }
        .view-section.active { display: block; animation: fadeIn 0.4s ease; }
        @keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }

        .card { background: white; border-radius: 20px; border: 1px solid #e2e8f0; padding: 1.5rem; margin-bottom: 2rem; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.05); }
        h1 { font-size: 2.25rem; font-weight: 700; letter-spacing: -1px; margin-bottom: 2rem; }

        /* Dynamic Table */
        table { width: 100%; border-collapse: collapse; }
        th { text-align: left; padding: 0.75rem; font-size: 0.75rem; color: #64748b; text-transform: uppercase; border-bottom: 1px solid #f1f5f9; }
        td { padding: 1rem 0.75rem; border-bottom: 1px solid #f1f5f9; font-size: 0.85rem; font-weight: 500; }
        
        /* Developer & DSC Console */
        .grid { display: grid; grid-template-columns: 1fr 1fr; gap: 2rem; }
        textarea { width: 100%; height: 400px; padding: 1.25rem; border-radius: 12px; border: 1px solid #e2e8f0; background: #f8fafc; font-family: 'JetBrains Mono', monospace; font-size: 0.8rem; resize: none; outline: none; }
        .response { background: #0f172a; color: #38bdf8; border-radius: 12px; padding: 1.25rem; height: 400px; overflow: auto; font-family: 'JetBrains Mono', monospace; font-size: 0.8rem; }
        .btn-run { background: var(--primary); color: white; border: none; padding: 0.75rem 2rem; border-radius: 8px; font-weight: 700; cursor: pointer; transition: 0.2s; margin-bottom: 1rem; }

        select { padding: 0.75rem; border-radius: 8px; border: 1px solid #e2e8f0; outline: none; margin-bottom: 1rem; width: 100%; font-weight: 600; }
    </style>
</head>
<body>

<div class="sidebar">
    <div class="logo">PMAY Master</div>
    <div onclick="nav('dash')" id="nav-dash" class="nav-item active">📊 Executive Dashboard</div>
    <div onclick="nav('states')" id="nav-states" class="nav-item">📍 State Explorer</div>
    <div onclick="nav('dsc')" id="nav-dsc" class="nav-item">🛡️ Digital Security (DSC)</div>
    <div onclick="nav('api')" id="nav-api" class="nav-item" style="margin-top:auto;">⚙️ Developer Suite</div>
</div>

<div class="main">
    <!-- DASHBOARD -->
    <div id="view-dash" class="view-section active">
        <h1>Executive Hub</h1>
        <div class="card" style="height:400px;"><canvas id="mainChart"></canvas></div>
    </div>

    <!-- STATE EXPLORER -->
    <div id="view-states" class="view-section">
        <h1>State Explorer</h1>
        <div class="card">
            <button class="btn-run" onclick="fetchStates()">REFRESH LIVE DATA</button>
            <table>
                <thead>
                    <tr><th>SLS Code</th><th>State Name</th><th>LGD Code</th><th>Local Name</th></tr>
                </thead>
                <tbody id="stateTable">
                    <tr><td colspan="4">No data loaded. Click Refresh.</td></tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- DSC SECURITY CENTRE -->
    <div id="view-dsc" class="view-section">
        <h1>Digital Security (DSC)</h1>
        <div class="grid">
            <div class="card">
                <h3>Enroll New Certificate</h3>
                <div style="display:grid; gap:1rem; margin-top:1rem;">
                    <input id="dName" placeholder="Applicant Name" value="Pritam Sharma" style="padding:0.75rem; border-radius:8px; border:1px solid #e2e8f0;">
                    <input id="dAadhaar" placeholder="Aadhaar" value="1234-5678-9012" style="padding:0.75rem; border-radius:8px; border:1px solid #e2e8f0;">
                </div>
                <button class="btn-run" onclick="enrollDsc()" style="margin-top:1rem;">GENERATE & ENROLL</button>
            </div>
            <div class="card">
                <h3>DSC Response Workflow</h3>
                <pre class="response" id="dscRes">// Results will appear here...</pre>
            </div>
        </div>
    </div>

    <!-- DEVELOPER SUITE -->
    <div id="view-api" class="view-section">
        <h1>Developer Suite</h1>
        <div class="grid">
            <div class="card">
                <select id="apiSelector" onchange="updatePayload()">
                    <option value="getState">📍 Get State Details</option>
                    <option value="getSNADetails">🏛️ SNA Information</option>
                    <option value="AgencyMapping">🏢 Agency Mapping</option>
                    <option value="Deduction">📉 Get Deductions</option>
                    <option value="Components">🧩 Integrated Components</option>
                    <option value="DDOAllotment">💰 DDO Allotment</option>
                    <option value="MotherSanction">📑 Mother Sanction</option>
                    <option value="FTOSubmit">📤 FTO Submission (XML)</option>
                    <option value="ErrorMaster">❌ Error Master Tools</option>
                </select>
                <button class="btn-run" onclick="execApi()">EXECUTE API</button>
                <textarea id="payload">{"slsCode": "UP"}</textarea>
            </div>
            <div class="card">
                <h3>Response Feedback</h3>
                <pre class="response" id="apiRes">// Result appearing here...</pre>
            </div>
        </div>
    </div>
</div>

<script>
    const apiHub = {
        'getState': { url:'/api/v1/getState', type:'JSON', body:{"slsCode":"UP"} },
        'getSNADetails': { url:'/api/v1/getSNADetails', type:'JSON', body:{"slsCode":"UP","finYear":"2023-24"} },
        'AgencyMapping': { url:'/api/v1/AgencyMappingDetails', type:'JSON', body:{"slsCode":"UP"} },
        'Deduction': { url:'/api/v1/getDeduction', type:'JSON', body:{"slsCode":"UP"} },
        'Components': { url:'/api/v1/getComponents', type:'JSON', body:{"slsCode":"UP"} },
        'DDOAllotment': { url:'/api/v1/ddoAllotmentDetails', type:'JSON', body:{"slsCode":"UP","ddoCode":"DDO123"} },
        'MotherSanction': { url:'/api/v1/motherSanctionLimit', type:'JSON', body:{"slsCode":"UP","sanctionNo":"SAN001"} },
        'FTOSubmit': { url:'/api/v1/ftoSubmit', type:'XML', body:'<?xml version="1.0" encoding="UTF-8"?><FtoRequest><FtoNumber>FTO123</FtoNumber></FtoRequest>' },
        'ErrorMaster': { url:'/api/v1/getErrorMaster', type:'JSON', body:{"errorCode":"E001"} }
    };

    function updatePayload() {
        const key = document.getElementById('apiSelector').value;
        const config = apiHub[key];
        document.getElementById('payload').value = (config.type === 'JSON') ? JSON.stringify(config.body, null, 2) : config.body;
    }

    async function execApi() {
        const key = document.getElementById('apiSelector').value;
        const config = apiHub[key];
        const box = document.getElementById('apiRes');
        box.innerText = "Executing...";
        try {
            const r = await fetch(config.url, {
                method: 'POST', headers: {'Content-Type': config.type==='JSON'?'application/json':'application/xml','Authorization':'Basic ' + btoa('ifmisuser:ifmispassword')},
                body: document.getElementById('payload').value
            });
            const text = await r.text();
            try { box.innerText = JSON.stringify(JSON.parse(text), null, 2); } catch { box.innerText = text; }
        } catch(e) { box.innerText = "Error: " + e.message; }
    }

    function nav(id) {
        document.querySelectorAll('.view-section, .nav-item').forEach(e => e.classList.remove('active'));
        document.getElementById('view-' + id).classList.add('active');
        document.getElementById('nav-' + id).classList.add('active');
        if(id === 'states') fetchStates();
    }

    async function fetchStates() {
        const table = document.getElementById('stateTable');
        table.innerHTML = "<tr><td colspan='4'>Fetching National Data...</td></tr>";
        try {
            const r = await fetch('/api/v1/allStates', {
                method: 'POST', headers: {'Authorization':'Basic ' + btoa('ifmisuser:ifmispassword')}
            });
            const d = await r.json();
            table.innerHTML = d.result.map(s => `<tr><td>${s.state_Code}</td><td>${s.state_Name}</td><td>${s.lgd_State_Code}</td><td>${s.st_local_name}</td></tr>`).join('');
        } catch(e) { table.innerHTML = "<tr><td colspan='4'>Error: " + e.message + "</td></tr>"; }
    }

    async function enrollDsc() {
        const box = document.getElementById('dscRes');
        box.innerText = "Generating XML...";
        const xml = `<?xml version="1.0" encoding="UTF-8"?><DscEnrollmentRequest><ApplicantName>${document.getElementById('dName').value}</ApplicantName><Aadhaar>${document.getElementById('dAadhaar').value}</Aadhaar></DscEnrollmentRequest>`;
        try {
            const r = await fetch('/api/v1/dscEnrollment', {
                method: 'POST', headers: {'Content-Type':'application/xml','Authorization':'Basic ' + btoa('ifmisuser:ifmispassword')},
                body: xml
            });
            box.innerText = await r.text();
        } catch(e) { box.innerText = "Error: " + e.message; }
    }

    new Chart(document.getElementById('mainChart').getContext('2d'), {
        type: 'line',
        data: {
            labels: ['09:00', '10:00', '11:00', '12:00', '13:00'],
            datasets: [{ label: 'Integration Traffic', data: [50, 120, 80, 200, 150], borderColor:'#4f46e5', tension:0.4, fill:true, backgroundColor:'rgba(79, 70, 229, 0.05)' }]
        },
        options: { maintainAspectRatio:false, plugins:{legend:{display:false}}, scales:{y:{beginAtZero:true}} }
    });
</script>
</body>
</html>
