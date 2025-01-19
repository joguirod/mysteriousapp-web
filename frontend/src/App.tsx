import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Dashboard from './pages/Dashboard';
import Historico from './pages/Historico';
import Pedidos from './pages/Pedidos';
import Produtos from './pages/Produtos';
import Livros from './pages/Livros';

function App() {
  return (
    <Router>
      <div className="flex">
        <Sidebar />
        <div className="ml-64 flex-1 p-8 bg-gray-100">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/historico" element={<Historico />} />
            <Route path="/pedidos" element={<Pedidos />} />
            <Route path="/produtos" element={<Produtos />} />
            <Route path="/livros" element={<Livros />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
