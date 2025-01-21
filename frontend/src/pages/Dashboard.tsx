import { useState } from 'react';
import Chart from 'react-apexcharts';

const Dashboard = () => {
  const [selectedMonth, setSelectedMonth] = useState('Janeiro');

  //Gráfico de colunas
  const purchasesMonthlyData = {
    options: {
      chart: { id: 'purchases-monthly' },
      xaxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      },
      title: { text: 'Compras Mensais' },
    },
    series: [{ name: 'Compras', data: [30, 40, 45, 50, 49, 60, 70, 91, 125, 80, 95, 110] }],
  };

  //Gráfico de pizza
  const popularOrdersData = {
    options: {
      labels: ['Produto A', 'Produto B', 'Produto C', 'Produto D'],
      title: { text: `Mais Pedidos em ${selectedMonth}` },
    },
    series: [44, 55, 13, 43],
  };

  const genreSalesData = {
    options: {
      chart: { id: 'genre-sales' },
      xaxis: {
        categories: ['Ficção', 'Romance', 'Terror', 'Infantil', 'Tecnologia'],
      },
      title: { text: `Gênero Mais Vendido em ${selectedMonth}` },
    },
    series: [{ name: 'Vendas', data: [120, 150, 90, 80, 100] }],
  };

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

      <div className="mt-10 flex flex-col space-y-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Compras Mensais</h2>
          <Chart options={purchasesMonthlyData.options} series={purchasesMonthlyData.series} type="bar" height={350} />
        </div>

        <div className="bg-white shadow-lg rounded-lg p-6">
          <div className="flex justify-between items-center">
            <h2 className="text-xl font-semibold mb-4">Mais Pedidos do Mês</h2>
            <select
              className="border rounded-lg p-2"
              value={selectedMonth}
              onChange={(e) => setSelectedMonth(e.target.value)}
            >
              {['Janeiro', 'Fevereiro', 'Março', 'Abril'].map((month) => (
                <option key={month} value={month}>
                  {month}
                </option>
              ))}
            </select>
          </div>
          <Chart options={popularOrdersData.options} series={popularOrdersData.series} type="pie" height={350} />
        </div>

        <div className="bg-white shadow-lg rounded-lg p-6">
          <div className="flex justify-between items-center">
            <h2 className="text-xl font-semibold mb-4">Gênero Mais Vendido</h2>
            <select
              className="border rounded-lg p-2"
              value={selectedMonth}
              onChange={(e) => setSelectedMonth(e.target.value)}
            >
              {['January', 'February', 'March', 'April'].map((month) => (
                <option key={month} value={month}>
                  {month}
                </option>
              ))}
            </select>
          </div>
          <Chart options={genreSalesData.options} series={genreSalesData.series} type="bar" height={350} />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
