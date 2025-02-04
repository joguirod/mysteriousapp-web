import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Dashboard from './pages/Dashboard';
import Historico from './pages/Historico';
import Pedidos from './pages/Pedidos';
import Produtos from './pages/Produtos';
// import Livros from './pages/Livros';
import Login from './pages/Login';

function Layout() {
  const location = useLocation();
  const hideSidebar = location.pathname === "/"; // Esconde a Sidebar apenas na tela de login

  return (
    <div className="flex">
      {!hideSidebar && <Sidebar />} {/* Sidebar só aparece fora da tela de login */}

      <div className={`flex-1 p-8 bg-gray-100 ${!hideSidebar ? "ml-64" : "flex justify-center items-center h-screen"}`}>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/historico" element={<Historico />} />
          <Route path="/pedidos" element={<Pedidos />} />
          <Route path="/produtos" element={<Produtos />} />
          {/* <Route path="/livros" element={<Livros />} /> */}
        </Routes>
      </div>
    </div>
  );
}

function App() {
  return (
    <Router>
      <Layout />
    </Router>
  );
}

export default App;
