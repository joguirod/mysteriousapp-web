import { useEffect, useState } from 'react';
import axios from 'axios';

interface OrderItem {
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

const Pedidos = () => {
  const [pedidos, setPedidos] = useState<Order[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [showModal, setShowModal] = useState<boolean>(false);
  const [newOrder, setNewOrder] = useState<OrderItem[]>([
    { productId: 0, quantity: 0, price: 0 },
  ]);

  useEffect(() => {
    fetchPedidos();
  }, []);

  // Buscar pedidos em andamento
  const fetchPedidos = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/order');
      const pedidosOngoing = response.data.filter((order: Order) => order.finishDate !== '');
      setPedidos(pedidosOngoing);
    } catch (err) {
      setError('Erro ao carregar pedidos.');
      console.error('Erro ao buscar pedidos:', err);
    } finally {
      setLoading(false);
    }
  };

  // Teste para criar um novo pedido 
  const handleCreateOrder = async () => {
    const mysteriousUserId = localStorage.getItem('mysteriousUserId');
    const mysteriousCustomerId = '3fa85f64-5717-4562-b3fc-2c963f66afa6'
    console.log(mysteriousUserId);
    if (!mysteriousUserId) {
      alert('Usuário não está logado. Por favor, faça login.');
      return;
    }

    if (newOrder.length === 0 || newOrder.some(item => item.productId === 0 || item.quantity === 0 || item.price === 0)) {
      alert("Preencha todos os campos corretamente.");
      return;
    }

    const formattedOrder = {
      mysteriousCustomerId: mysteriousCustomerId,
      items: newOrder.map((item) => ({
        productId: item.productId,
        quantity: item.quantity,
        price: item.price,
      })),
    };

    console.log('Objeto de pedido:', JSON.stringify(formattedOrder, null, 2));

    try {
      await axios.post('http://localhost:8080/api/order', formattedOrder, {
        headers: { 'Content-Type': 'application/json' },
      });
      alert('Pedido criado com sucesso!');
      setShowModal(false);
      fetchPedidos();
    } catch (error) {
      console.error('Erro ao criar pedido:', error);
      alert('Erro ao criar pedido. Verifique os dados e tente novamente.');
    }
  };

  return (
    <div className="w-full h-full flex flex-col">
      <h1 className="text-4xl font-bold mb-6">Pedidos em Andamento</h1>

      <button
        className="btn-primary self-start mb-6"
        onClick={() => setShowModal(true)}
      >
        Criar Pedido
      </button>

      {loading ? (
        <p className="text-lg text-gray-600">Carregando pedidos...</p>
      ) : error ? (
        <p className="text-lg text-red-600">{error}</p>
      ) : (
        <div className="grid grid-cols-2 gap-6">
          {pedidos.map((pedido) => (
            <div key={pedido.orderId} className="bg-white shadow-lg rounded-lg p-6">
              <h2 className="text-xl font-semibold text-gray-800">Pedido #{pedido.orderId}</h2>
              <p className="text-gray-600">Total: R$ {pedido.totalValue.toFixed(2)}</p>
              <p className="text-gray-500">Itens: {pedido.items.length}</p>
            </div>
          ))}
        </div>
      )}

      {showModal && (
        <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-2xl">
            <h2 className="text-2xl font-bold mb-4">Criar Novo Pedido</h2>

            <div className="grid grid-cols-3 gap-4 mb-4">
              {newOrder.map((item, index) => (
                <div key={index} className="border p-4 rounded-lg shadow-md">
                  <input
                    type="number"
                    placeholder="ID do Produto"
                    className="w-full border rounded-lg p-2 mb-2"
                    value={item.productId}
                    onChange={(e) => {
                      const updatedItems = [...newOrder];
                      updatedItems[index].productId = parseInt(e.target.value);
                      setNewOrder(updatedItems);
                    }}
                  />
                  <input
                    type="number"
                    placeholder="Quantidade"
                    className="w-full border rounded-lg p-2 mb-2"
                    value={item.quantity}
                    onChange={(e) => {
                      const updatedItems = [...newOrder];
                      updatedItems[index].quantity = parseInt(e.target.value);
                      setNewOrder(updatedItems);
                    }}
                  />
                  <input
                    type="number"
                    placeholder="Preço"
                    className="w-full border rounded-lg p-2 mb-2"
                    value={item.price}
                    onChange={(e) => {
                      const updatedItems = [...newOrder];
                      updatedItems[index].price = parseFloat(e.target.value);
                      setNewOrder(updatedItems);
                    }}
                  />
                </div>
              ))}
            </div>

            <button
              className="btn-secondary mr-2"
              onClick={() => setNewOrder([...newOrder, { productId: 0, quantity: 0, price: 0 }])}
            >
              Adicionar Item
            </button>

            <div className="flex justify-end space-x-4 mt-4">
              <button className="btn-secondary" onClick={() => setShowModal(false)}>
                Cancelar
              </button>
              <button className="btn-primary" onClick={handleCreateOrder}>
                Criar Pedido
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Pedidos;
