import { FaTachometerAlt, FaHistory, FaClipboardList, FaBox, FaBook, FaSignOutAlt } from 'react-icons/fa';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import logo from '../images/mysterious.jpg';

const Sidebar = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('userToken');
    localStorage.removeItem('mysteriousUserId');
    navigate('/');
  };

  return (
    <div className="w-64 h-screen bg-gray-900 text-white fixed flex flex-col justify-between">
      <div className="p-6 flex items-center space-x-4 border-b border-gray-700">
        <img src={logo} alt="Logo" className="h-12 w-12 rounded-full" />
        <h1 className="Mysterious text-2xl">Mysterious</h1>
      </div>

      <ul className="flex-1 p-4 space-y-4">
        <SidebarItem icon={<FaTachometerAlt />} text="Dashboard" link="/dashboard" active={location.pathname === '/dashboard'} />
        <SidebarItem icon={<FaHistory />} text="Histórico de Pedidos" link="/historico" active={location.pathname === '/historico'} />
        <SidebarItem icon={<FaClipboardList />} text="Pedidos em Andamento" link="/pedidos" active={location.pathname === '/pedidos'} />
        <SidebarItem icon={<FaBox />} text="Produtos" link="/produtos" active={location.pathname === '/produtos'} />
        {/* <SidebarItem icon={<FaBook />} text="Livros" link="/livros" active={location.pathname === '/livros'} /> */}
      </ul>

      <div className="border-t border-gray-700 p-4">
        <button
          onClick={handleLogout}
          className="flex items-center space-x-3 p-3 rounded-lg cursor-pointer transition bg-red-600 hover:bg-red-700 w-full"
        >
          <FaSignOutAlt className="text-xl" />
          <span className="text-lg">Logout</span>
        </button>
      </div>
    </div>
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
        {icon}
        <span className="text-lg">{text}</span>
      </li>
    </Link>
  );
};

export default Sidebar;
