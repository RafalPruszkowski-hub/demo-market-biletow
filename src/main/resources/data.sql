-- Insert Events (Future events)
INSERT INTO events (id, public_id, name, date, location, description, image_url) VALUES
(1, '550e8400-e29b-41d4-a716-446655440001', 'Rock Festival 2023', '2023-01-14 18:00:00', 'Warsaw, National Stadium', 'The biggest rock music festival featuring international and local bands', 'https://images.unsplash.com/photo-1501281668745-f7f57925c3b4?w=400'),
(2, '550e8400-e29b-41d4-a716-446655440002', 'Tech Conference', '2023-01-29 09:00:00', 'Krakow, ICE Congress Centre', 'Annual technology conference with keynotes from industry leaders', 'https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=400'),
(3, '550e8400-e29b-41d4-a716-446655440003', 'Classical Music Night', '2025-12-30 19:00:00', 'Wroclaw, National Forum of Music', 'Evening of classical masterpieces performed by philharmonic orchestra', 'https://images.unsplash.com/photo-1507838153414-b4b713384a76?w=400'),
(4, '550e8400-e29b-41d4-a716-446655440004', 'Food & Wine Festival', '2026-02-13 12:00:00', 'Gdansk, Old Town', 'Culinary experience featuring local and international cuisine', 'https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=400'),
(5, '550e8400-e29b-41d4-a716-446655440005', 'Art Exhibition Opening', '2025-12-25 17:00:00', 'Poznan, National Museum', 'Contemporary art exhibition showcasing emerging artists', 'https://images.unsplash.com/photo-1460661419201-fd4cecdf8a8b?w=400'),
(6, '550e8400-e29b-41d4-a716-446655440006', 'Jazz in the Park', '2026-01-09 20:00:00', 'Lodz, Central Park', 'Open-air jazz concert under the stars with famous performers', 'https://images.unsplash.com/photo-1415201364774-f6f0bb35f28f?w=400'),
(7, '550e8400-e29b-41d4-a716-446655440007', 'Startup Summit', '2026-02-03 10:00:00', 'Warsaw, Copernicus Science Centre', 'Networking event for entrepreneurs and investors', 'https://images.unsplash.com/photo-1511578314322-379afb476865?w=400'),
(8, '550e8400-e29b-41d4-a716-446655440008', 'Theater Performance: Hamlet', '2026-01-04 19:30:00', 'Krakow, Slowacki Theatre', 'Classic Shakespeare tragedy in modern interpretation', 'https://images.unsplash.com/photo-1503095396549-807759245b35?w=400'),
(9, '550e8400-e29b-41d4-a716-446655440009', 'Marathon 2025', '2026-03-15 08:00:00', 'Warsaw, City Center', 'Annual city marathon - 42km race through historic streets', 'https://images.unsplash.com/photo-1452626038306-9aae5e071dd3?w=400');

-- Insert Tickets for Event 1
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(1, '660e8400-e29b-41d4-a716-446655440001', 99.99, 119.99, 'TKT-001', 'AVAILABLE', 'STANDARD', 1),
(2, '660e8400-e29b-41d4-a716-446655440002', 149.99, 149.99, 'TKT-002', 'AVAILABLE', 'STANDARD', 1),
(3, '660e8400-e29b-41d4-a716-446655440003', 199.99, 249.99, 'TKT-003', 'AVAILABLE', 'VIP', 1),
(4, '660e8400-e29b-41d4-a716-446655440004', 249.99, 249.99, 'TKT-004', 'RESERVED', 'VIP', 1),
(5, '660e8400-e29b-41d4-a716-446655440005', 299.99, 399.99, 'TKT-005', 'AVAILABLE', 'PREMIUM', 1);

-- Insert Tickets for Event 2
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(6, '660e8400-e29b-41d4-a716-446655440006', 199.99, 199.99, 'TKT-006', 'AVAILABLE', 'PREMIUM', 2),
(7, '660e8400-e29b-41d4-a716-446655440007', 249.99, 299.99, 'TKT-007', 'AVAILABLE', 'VIP', 2),
(8, '660e8400-e29b-41d4-a716-446655440008', 299.99, 299.99, 'TKT-008', 'AVAILABLE', 'VIP', 2),
(9, '660e8400-e29b-41d4-a716-446655440009', 349.99, 449.99, 'TKT-009', 'RESERVED', 'PREMIUM', 2),
(10, '660e8400-e29b-41d4-a716-446655440010', 399.99, 499.99, 'TKT-010', 'AVAILABLE', 'PREMIUM', 2);

-- Insert Tickets for Event 3
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(11, '660e8400-e29b-41d4-a716-446655440011', 79.99, 89.99, 'TKT-011', 'AVAILABLE', 'STANDARD', 3),
(12, '660e8400-e29b-41d4-a716-446655440012', 99.99, 99.99, 'TKT-012', 'AVAILABLE', 'STANDARD', 3),
(13, '660e8400-e29b-41d4-a716-446655440013', 129.99, 149.99, 'TKT-013', 'AVAILABLE', 'VIP', 3),
(14, '660e8400-e29b-41d4-a716-446655440014', 159.99, 159.99, 'TKT-014', 'RESERVED', 'VIP', 3),
(15, '660e8400-e29b-41d4-a716-446655440015', 189.99, 229.99, 'TKT-015', 'AVAILABLE', 'PREMIUM', 3);

-- Insert Tickets for Event 4
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(16, '660e8400-e29b-41d4-a716-446655440016', 129.99, 129.99, 'TKT-016', 'AVAILABLE', 'STANDARD', 4),
(17, '660e8400-e29b-41d4-a716-446655440017', 169.99, 189.99, 'TKT-017', 'AVAILABLE', 'STANDARD', 4),
(18, '660e8400-e29b-41d4-a716-446655440018', 199.99, 199.99, 'TKT-018', 'AVAILABLE', 'VIP', 4),
(19, '660e8400-e29b-41d4-a716-446655440019', 229.99, 279.99, 'TKT-019', 'RESERVED', 'PREMIUM', 4),
(20, '660e8400-e29b-41d4-a716-446655440020', 259.99, 289.99, 'TKT-020', 'AVAILABLE', 'PREMIUM', 4);

-- Insert Tickets for Event 5
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(21, '660e8400-e29b-41d4-a716-446655440021', 49.99, 59.99, 'TKT-021', 'AVAILABLE', 'STANDARD', 5),
(22, '660e8400-e29b-41d4-a716-446655440022', 69.99, 79.99, 'TKT-022', 'AVAILABLE', 'STANDARD', 5),
(23, '660e8400-e29b-41d4-a716-446655440023', 89.99, 99.99, 'TKT-023', 'AVAILABLE', 'VIP', 5),
(24, '660e8400-e29b-41d4-a716-446655440024', 109.99, 109.99, 'TKT-024', 'RESERVED', 'VIP', 5),
(25, '660e8400-e29b-41d4-a716-446655440025', 129.99, 159.99, 'TKT-025', 'AVAILABLE', 'PREMIUM', 5);

-- Insert Tickets for Event 6
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(26, '660e8400-e29b-41d4-a716-446655440026', 89.99, 99.99, 'TKT-026', 'AVAILABLE', 'STANDARD', 6),
(27, '660e8400-e29b-41d4-a716-446655440027', 119.99, 119.99, 'TKT-027', 'AVAILABLE', 'STANDARD', 6),
(28, '660e8400-e29b-41d4-a716-446655440028', 149.99, 169.99, 'TKT-028', 'AVAILABLE', 'VIP', 6),
(29, '660e8400-e29b-41d4-a716-446655440029', 179.99, 199.99, 'TKT-029', 'RESERVED', 'VIP', 6),
(30, '660e8400-e29b-41d4-a716-446655440030', 209.99, 259.99, 'TKT-030', 'AVAILABLE', 'PREMIUM', 6);

-- Insert Tickets for Event 7
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(31, '660e8400-e29b-41d4-a716-446655440031', 149.99, 179.99, 'TKT-031', 'AVAILABLE', 'STANDARD', 7),
(32, '660e8400-e29b-41d4-a716-446655440032', 199.99, 249.99, 'TKT-032', 'AVAILABLE', 'VIP', 7),
(33, '660e8400-e29b-41d4-a716-446655440033', 249.99, 249.99, 'TKT-033', 'AVAILABLE', 'VIP', 7),
(34, '660e8400-e29b-41d4-a716-446655440034', 299.99, 399.99, 'TKT-034', 'RESERVED', 'PREMIUM', 7),
(35, '660e8400-e29b-41d4-a716-446655440035', 349.99, 449.99, 'TKT-035', 'AVAILABLE', 'PREMIUM', 7);

-- Insert Tickets for Event 8
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(36, '660e8400-e29b-41d4-a716-446655440036', 59.99, 69.99, 'TKT-036', 'AVAILABLE', 'STANDARD', 8),
(37, '660e8400-e29b-41d4-a716-446655440037', 79.99, 79.99, 'TKT-037', 'AVAILABLE', 'STANDARD', 8),
(38, '660e8400-e29b-41d4-a716-446655440038', 99.99, 119.99, 'TKT-038', 'AVAILABLE', 'VIP', 8),
(39, '660e8400-e29b-41d4-a716-446655440039', 119.99, 129.99, 'TKT-039', 'RESERVED', 'VIP', 8),
(40, '660e8400-e29b-41d4-a716-446655440040', 139.99, 179.99, 'TKT-040', 'AVAILABLE', 'PREMIUM', 8);

-- Insert Tickets for Event 9
INSERT INTO tickets (id, public_id, price, original_price, ticket_number, status, category, event_id) VALUES
(41, '660e8400-e29b-41d4-a716-446655440041', 39.99, 49.99, 'TKT-041', 'AVAILABLE', 'STANDARD', 9),
(42, '660e8400-e29b-41d4-a716-446655440042', 49.99, 49.99, 'TKT-042', 'AVAILABLE', 'STANDARD', 9),
(43, '660e8400-e29b-41d4-a716-446655440043', 59.99, 69.99, 'TKT-043', 'AVAILABLE', 'STANDARD', 9),
(44, '660e8400-e29b-41d4-a716-446655440044', 69.99, 79.99, 'TKT-044', 'RESERVED', 'VIP', 9),
(45, '660e8400-e29b-41d4-a716-446655440045', 79.99, 99.99, 'TKT-045', 'AVAILABLE', 'VIP', 9);

-- Reset sequences to continue after manual inserts
ALTER TABLE events ALTER COLUMN id RESTART WITH 10;
ALTER TABLE tickets ALTER COLUMN id RESTART WITH 46;
