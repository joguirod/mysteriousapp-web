const Dashboard = () => {
  return (
    <div className="w-full h-full flex flex-col">
      <h1 className="text-4xl font-bold mb-6">Dashboard</h1>

      <div className="grid grid-cols-2 gap-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Resumo Financeiro</h2>
          <div className="text-3xl font-bold text-green-600">$54,232</div>
        </div>

        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Total de Pedidos</h2>
          <div className="text-3xl font-bold text-blue-600">12,313</div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
