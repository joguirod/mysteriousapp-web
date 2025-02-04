import { useEffect, useState } from 'react';
import axios from 'axios';

interface OrderItem {
  itemId: number;
  productId: number;
  quantity: number;
  price: number;
}

interface Order {
  orderId: string;
  customerId: string;
  orderDate: string;
  finishDate: string | null; // Pode ser null
  totalValue: number;
  items: OrderItem[];
}

const Historico = () => {
  const [pedidosFinalizados, setPedidosFinalizados] = useState<Order[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [selectedOrder, setSelectedOrder] = useState<Order | null>(null);

  useEffect(() => {
    fetchHistorico();
  }, []);

  const fetchHistorico = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/order');
      
      console.log("📌 Dados da API:", response.data); // Log dos dados recebidos
  
      const pedidosFinalizados = response.data.filter((order: Order) => order.finishDate !== null);
      
      console.log("📌 Pedidos Finalizados:", pedidosFinalizados); // Log dos pedidos finalizados
  
      setPedidosFinalizados(pedidosFinalizados);
    } catch (err) {
      setError('Erro ao carregar histórico de pedidos.');
      console.error('Erro ao buscar histórico:', err);
    } finally {
      setLoading(false);
    }
  };
  

  return (
    <div className="w-full h-full flex flex-col">
      <h1 className="text-4xl font-bold mb-6">Histórico de Pedidos</h1>

      {loading ? (
        <p className="text-lg text-gray-600">Carregando histórico...</p>
      ) : error ? (
        <p className="text-lg text-red-600">{error}</p>
      ) : (
        <div className="grid grid-cols-2 gap-6">
          {pedidosFinalizados.map((pedido) => (
            <div
              key={pedido.orderId}
              className="bg-white shadow-lg rounded-lg p-6 cursor-pointer hover:shadow-xl transition"
              onClick={() => setSelectedOrder(pedido)}
            >
              <h2 className="text-xl font-semibold text-gray-800">Pedido #{pedido.orderId}</h2>
              <p className="text-gray-600">Total: R$ {pedido.totalValue.toFixed(2)}</p>
              <p className="text-gray-500">Itens: {pedido.items.length}</p>
              <p className="text-gray-500">
                Finalizado em:{' '}
                {pedido.finishDate ? new Date(pedido.finishDate).toLocaleDateString() : 'Não finalizado'}
              </p>
            </div>
          ))}
        </div>
      )}

      {selectedOrder && (
        <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-1/3">
            <h2 className="text-2xl font-bold mb-4">Detalhes do Pedido #{selectedOrder.orderId}</h2>
            <p><strong>Cliente ID:</strong> {selectedOrder.customerId}</p>
            <p><strong>Data do Pedido:</strong> {new Date(selectedOrder.orderDate).toLocaleDateString()}</p>
            <p><strong>Total:</strong> R$ {selectedOrder.totalValue.toFixed(2)}</p>
            <p><strong>Finalizado em:</strong> {selectedOrder.finishDate ? new Date(selectedOrder.finishDate).toLocaleDateString() : 'Ainda não finalizado'}</p>
            <button className="mt-4 px-4 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-900" onClick={() => setSelectedOrder(null)}>
              Fechar
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Historico;
