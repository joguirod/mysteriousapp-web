import { useState, useEffect } from "react";
import axios from "axios";
import Chart from "react-apexcharts";

const Dashboard = () => {
  const [selectedMonth, setSelectedMonth] = useState((new Date().getMonth() + 1).toString());
  const [totalOrders, setTotalOrders] = useState(0);
  const [totalIncome, setTotalIncome] = useState(0);
  const [purchasesData, setPurchasesData] = useState<{ description: string; totalSold: number }[]>([]);
  const [popularOrdersData, setPopularOrdersData] = useState<number[]>([]);
  const [genreSalesData, setGenreSalesData] = useState<{ description: string; totalSold: number }[]>([]);

  useEffect(() => {
    fetchTotalOrders();
    fetchTotalIncome();
    fetchPurchasesData();
    fetchPopularOrdersData();
    fetchGenreSalesData();
  }, [selectedMonth]);

  // Função para validar resposta JSON
  const isValidJson = (response: any) => {
    return response && typeof response === "object" && !response.toString().includes("<!DOCTYPE html>");
  };

  // Buscar total de pedidos
  const fetchTotalOrders = async () => {
    try {
      console.log(`📡 Buscando total de pedidos para o ano de 2025`);
  
      const response = await axios.get(`http://localhost:8080/api/order/year/2025`);
      
      console.log("✅ API Response:", response.data);
  
      if (Array.isArray(response.data)) {
        setTotalOrders(response.data.length);
      } else {
        console.error("⚠ Erro: resposta da API de total de pedidos não é válida", response.data);
      }
    } catch (error: any) {
      console.error("❌ Erro ao buscar total de pedidos:", error.message);
    }
  };
  

  // Buscar faturamento total
  const fetchTotalIncome = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/order/income/year/2025`);
      console.log("📊 Faturamento API Response:", response.data);

        setTotalIncome(response.data);
    } catch (error: any) {
      console.error("❌ Erro ao buscar faturamento:", error.message);
    }
  };

  // Buscar compras mensais
  const fetchPurchasesData = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/product/totalSold/category/month/${selectedMonth}`);
      console.log("📊 Compras Mensais API Response:", response.data);

      if (isValidJson(response.data)) {
        setPurchasesData(Array.isArray(response.data) ? response.data : []);
      } else {
        console.error("⚠ Erro: resposta da API de compras mensais não é válida", response.data);
      }
    } catch (error: any) {
      console.error("❌ Erro ao buscar compras mensais:", error.message);
    }
  };

  // Buscar pedidos populares
  const fetchPopularOrdersData = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/product/totalSold/category/month/${selectedMonth}`);
      console.log("📊 Pedidos Populares API Response:", response.data);

      if (isValidJson(response.data)) {
        setPopularOrdersData(Array.isArray(response.data) ? response.data.map((item) => item.totalSold) : []);
      } else {
        console.error("⚠ Erro: resposta da API de pedidos populares não é válida", response.data);
      }
    } catch (error: any) {
      console.error("❌ Erro ao buscar pedidos populares:", error.message);
    }
  };

  // Buscar vendas por gênero
  const fetchGenreSalesData = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/product/totalSold/genre/month/${selectedMonth}`);
      console.log("📊 Vendas por Gênero API Response:", response.data);

      if (isValidJson(response.data)) {
        setGenreSalesData(Array.isArray(response.data) ? response.data : []);
      } else {
        console.error("⚠ Erro: resposta da API de vendas por gênero não é válida", response.data);
      }
    } catch (error: any) {
      console.error("❌ Erro ao buscar vendas por gênero:", error.message);
    }
  };

  return (
    <div className="w-full h-full flex flex-col">
      <h1 className="text-4xl font-bold mb-6">Dashboard</h1>

      <div className="grid grid-cols-2 gap-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Resumo Financeiro</h2>
          <div className="text-3xl font-bold text-green-600">
            R$ {totalIncome.toFixed(2)}
          </div>
        </div>

        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Total de Pedidos</h2>
          <div className="text-3xl font-bold text-blue-600">{totalOrders}</div>
        </div>
      </div>

      <div className="mt-10 flex flex-col space-y-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Compras Mensais</h2>
          <Chart 
            options={{ 
              chart: { id: "purchases-monthly" },
              xaxis: { categories: purchasesData.map(item => item.description) }
            }} 
            series={[{ name: "Compras", data: purchasesData.map(item => item.totalSold) }]} 
            type="bar" 
            height={350} 
          />
        </div>

        <div className="bg-white shadow-lg rounded-lg p-6">
          <div className="flex justify-between items-center">
            <h2 className="text-xl font-semibold mb-4">Mais Pedidos do Mês</h2>
            <select
              className="border rounded-lg p-2"
              value={selectedMonth}
              onChange={(e) => setSelectedMonth(e.target.value)}
            >
              {Array.from({ length: 12 }, (_, i) => (i + 1).toString()).map((month) => (
                <option key={month} value={month}>
                  {`Mês ${month}`}
                </option>
              ))}
            </select>
          </div>
          <Chart options={{ labels: ["Premium", "Standard", "Luxo"] }} series={popularOrdersData} type="pie" height={350} />
        </div>

        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Gênero Mais Vendido</h2>
          <Chart options={{ 
              chart: { id: "genres-sales" },
              xaxis: { categories: genreSalesData.map(item => item.description) }
            }} series={[{ name: "Vendas", data: genreSalesData.map(item => item.totalSold) }]} type="bar" height={350} />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
