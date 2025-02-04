import { useState, useEffect } from "react";
import axios from "axios";
import Chart from "react-apexcharts";

const Dashboard = () => {
  const [selectedMonth, setSelectedMonth] = useState("1"); // Janeiro = 1
  const [totalOrders, setTotalOrders] = useState(0);
  const [totalIncome, setTotalIncome] = useState(0);
  const [purchasesData, setPurchasesData] = useState<number[]>([]);
  const [popularOrdersData, setPopularOrdersData] = useState<number[]>([]);
  const [genreSalesData, setGenreSalesData] = useState<number[]>([]);

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
      const response = await axios.get(`/api/order/income/month/${selectedMonth}`);
      console.log("📊 Faturamento API Response:", response.data);

      if (isValidJson(response.data) && typeof response.data === "number") {
        setTotalIncome(response.data);
      } else {
        console.error("⚠ Erro: resposta da API de faturamento não é válida", response.data);
      }
    } catch (error: any) {
      console.error("❌ Erro ao buscar faturamento:", error.message);
    }
  };

  // Buscar compras mensais
  const fetchPurchasesData = async () => {
    try {
      const response = await axios.get(`/api/product/totalSold/category/month/${selectedMonth}`);
      console.log("📊 Compras Mensais API Response:", response.data);

      if (isValidJson(response.data)) {
        setPurchasesData(Array.isArray(response.data) ? response.data.map((item) => item.totalSold) : []);
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
      const response = await axios.get(`/api/product/totalSold/category/month/${selectedMonth}`);
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
      const response = await axios.get(`/api/product/totalSold/genre/month/${selectedMonth}`);
      console.log("📊 Vendas por Gênero API Response:", response.data);

      if (isValidJson(response.data)) {
        setGenreSalesData(Array.isArray(response.data) ? response.data.map((item) => item.totalSold) : []);
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
          <Chart options={{ chart: { id: "purchases-monthly" } }} series={[{ name: "Compras", data: purchasesData }]} type="bar" height={350} />
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
          <Chart options={{ labels: ["Produto A", "Produto B", "Produto C", "Produto D"] }} series={popularOrdersData} type="pie" height={350} />
        </div>

        <div className="bg-white shadow-lg rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Gênero Mais Vendido</h2>
          <Chart options={{ chart: { id: "genre-sales" } }} series={[{ name: "Vendas", data: genreSalesData }]} type="bar" height={350} />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
