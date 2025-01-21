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
  finishDate: string;
  status: string;
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
      const pedidosFinalizados = response.data.filter((order: Order) => order.status !== 'ONGOING');
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
              className="bg-white shadow-lg rounded-lg p-6 cursor-pointer"
              onClick={() => setSelectedOrder(pedido)}
            >
              <h2 className="text-xl font-semibold text-gray-800">Pedido #{pedido.orderId}</h2>
              <p className="text-gray-600">Total: R$ {pedido.totalValue.toFixed(2)}</p>
              <p className="text-gray-500">Itens: {pedido.items.length}</p>
              <p className="text-gray-500">Finalizado em: {new Date(pedido.finishDate).toLocaleDateString()}</p>
            </div>
          ))}
        </div>
      )}

      {selectedOrder && (
        <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-1/3">
            <h2 className="text-2xl font-bold mb-4">Detalhes do Pedido #{selectedOrder.orderId}</h2>
            <p>Cliente ID: {selectedOrder.customerId}</p>
            <p>Data do Pedido: {new Date(selectedOrder.orderDate).toLocaleDateString()}</p>
            <p>Total: R$ {selectedOrder.totalValue.toFixed(2)}</p>
            <p>Status: {selectedOrder.status}</p>
            <button className="btn-secondary mt-4" onClick={() => setSelectedOrder(null)}>
              Fechar
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Historico;
