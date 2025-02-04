import { FaTachometerAlt, FaHistory, FaClipboardList, FaBox, FaBook, FaSignOutAlt } from 'react-icons/fa';
import { Link, useLocation } from 'react-router-dom';
import logo from '../images/mysterious.jpg';
import { useState } from 'react';
import axios from 'axios';

const Sidebar = () => {
  const location = useLocation();
  const [showModal, setShowModal] = useState(false);
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({ email: '', password: '', username: '' });

  const handleLoginSignup = async () => {
    const url = isLogin
      ? 'http://localhost:8080/api/mysteriousUser/auth/signin'
      : 'http://localhost:8080/api/mysteriousUser/auth/signup';

    const data = isLogin
      ? { email: formData.email, password: formData.password }
      : { username: formData.username, email: formData.email, password: formData.password };

    try {
      const response = await axios.post(url, data, {
        headers: { 'Content-Type': 'application/json' },
      });

      if (isLogin) {
        localStorage.setItem('userToken', response.data.token);
        localStorage.setItem('mysteriousUserId', response.data.mysteriousUser.mysteriousUserId);
        alert('Login realizado com sucesso!');
      } else {
        alert('Cadastro realizado com sucesso! Faça login para continuar.');
      }

      setShowModal(false);
      setFormData({ email: '', password: '', username: '' });
    } catch (error) {
      console.error('Erro ao autenticar:', error);
      alert('Erro ao autenticar. Verifique suas credenciais.');
    }
  };

  return (
    <>
      <div className="w-64 h-screen bg-gray-900 text-white fixed flex flex-col justify-between">
        <div className="p-6 flex items-center space-x-4 border-b border-gray-700">
          <img src={logo} alt="Logo" className="h-12 w-12 rounded-full" />
          <h1 className="Mysterious text-2xl">Mysterious</h1>
        </div>

        <ul className="flex-1 p-4 space-y-4">
          <SidebarItem icon={<FaTachometerAlt />} text="Dashboard" link="/" active={location.pathname === '/'} />
          <SidebarItem icon={<FaHistory />} text="Histórico de Pedidos" link="/historico" active={location.pathname === '/historico'} />
          <SidebarItem icon={<FaClipboardList />} text="Pedidos em Andamento" link="/pedidos" active={location.pathname === '/pedidos'} />
          <SidebarItem icon={<FaBox />} text="Produtos" link="/produtos" active={location.pathname === '/produtos'} />
          <SidebarItem icon={<FaBook />} text="Livros" link="/livros" active={location.pathname === '/livros'} />
          {/* <li
            className="flex items-center space-x-3 p-3 rounded-lg cursor-pointer transition hover:bg-gray-700"
            onClick={() => setShowModal(true)}
          >
            <span className="text-xl"><FaUser /></span>
            <span className="text-lg">Login / Cadastro</span>
          </li> */}
        </ul>

        <div className="border-t border-gray-700 p-4">
          <SidebarItem icon={<FaSignOutAlt />} text="Logout" link="/logout" active={location.pathname === '/logout'} />
        </div>
      </div>

      {showModal && (
        <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-md">
            <h2 className="text-2xl font-bold mb-4">{isLogin ? 'Login' : 'Cadastro'}</h2>

            {!isLogin && (
              <input
                type="text"
                placeholder="Nome de usuário"
                className="w-full border rounded-lg p-2 mb-4"
                value={formData.username}
                onChange={(e) => setFormData({ ...formData, username: e.target.value })}
              />
            )}

            <input
              type="email"
              placeholder="E-mail"
              className="w-full border rounded-lg p-2 mb-4"
              value={formData.email}
              onChange={(e) => setFormData({ ...formData, email: e.target.value })}
            />
            <input
              type="password"
              placeholder="Senha"
              className="w-full border rounded-lg p-2 mb-4"
              value={formData.password}
              onChange={(e) => setFormData({ ...formData, password: e.target.value })}
            />

            <div className="flex justify-between items-center">
              <button
                className="text-blue-500 underline"
                onClick={() => setIsLogin(!isLogin)}
              >
                {isLogin ? 'Não tem uma conta? Cadastre-se' : 'Já tem uma conta? Faça login'}
              </button>
              <div className="flex space-x-4">
                <button className="btn-secondary" onClick={() => setShowModal(false)}>Cancelar</button>
                <button className="btn-primary" onClick={handleLoginSignup}>
                  {isLogin ? 'Entrar' : 'Cadastrar'}
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

const SidebarItem = ({ icon, text, link, active }: { icon: JSX.Element; text: string; link: string; active: boolean }) => {
  return (
    <Link to={link}>
      <li
        className={`flex items-center space-x-3 p-3 rounded-lg cursor-pointer transition ${
          active ? 'bg-blue-500' : 'hover:bg-gray-700'
        }`}
      >
        <span className="text-xl">{icon}</span>
        <span className="text-lg">{text}</span>
      </li>
    </Link>
  );
};

export default Sidebar;
