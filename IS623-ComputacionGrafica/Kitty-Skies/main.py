import pygame
import random
from contants.parameters import get_screen_width, get_screen_height, get_white, get_black, get_pipe_height, get_pipe_frequency, get_gravity, get_flap_strength, get_pipe_width, get_pipe_gap, get_pipe_speed

class Box(pygame.sprite.Sprite):
	def __init__(self):
		super().__init__()
		self.image = pygame.image.load('assets/variosGatos.png').convert_alpha()
		self.image = pygame.transform.scale(self.image, (150, 300))
		self.rect = self.image.get_rect()
		self.rect.x = get_screen_width()
		self.rect.y = get_screen_height() // 2

	def update(self):
		self.rect.x -= 5
		if self.rect.right < 0:
			self.kill()

class Bird(pygame.sprite.Sprite):
	def __init__(self, screen):
		super().__init__()
		self.screen = screen
		self.bird_image = pygame.image.load('assets/kitty.png').convert_alpha()
		self.bird_image = pygame.transform.scale(self.bird_image, (80, 50))
		self.image = self.bird_image
		self.rect = self.image.get_rect(center=(100, get_screen_height() // 2))
		self.velocity = 0

	def update(self):
		self.velocity += get_gravity()
		self.rect.y += self.velocity
		if self.rect.top <= 0 or self.rect.bottom >= get_screen_height():
			self.kill()
			game_over(self.screen)

	def flap(self):
		self.velocity = get_flap_strength()

class Pipe(pygame.sprite.Sprite):
	def __init__(self, x, y, is_top):
		self.pipe_image = pygame.image.load('assets/obstáculo.png').convert_alpha()
		self.pipe_image = pygame.transform.scale(self.pipe_image, (get_pipe_width(), get_pipe_height()))
		super().__init__()
		self.image = self.pipe_image
		if is_top: 
			self.image = pygame.transform.flip(self.image, False, True)
		self.rect = self.image.get_rect(topleft=(x, y) if is_top else (x, y + get_pipe_gap()))

	def update(self):
		self.rect.x -= get_pipe_speed()
		if self.rect.right < 0:
			self.kill()

class ShooterBox(pygame.sprite.Sprite):
	def __init__(self, position):
		super().__init__()
		self.dron_image = pygame.image.load('assets/dron.png').convert_alpha()
		self.dron_image = pygame.transform.scale(self.dron_image, (120, 120))
		self.image = self.dron_image
		self.rect = self.image.get_rect()
		if position == 'top':
			self.rect.topleft = (get_screen_width() - 120, 0)
		else:
			self.rect.bottomleft = (get_screen_width() - 120, get_screen_height())
		self.has_shot = False

	def shoot(self, target):
		self.has_shot = True
		bullet = Bullet(self.rect.center, target)
		return bullet

class Bullet(pygame.sprite.Sprite):
	def __init__(self, position, target):
		super().__init__()
		self.image = pygame.Surface((12, 8))
		self.image.fill((255, 255, 0))
		self.rect = self.image.get_rect(center=position)
		self.speed = 5
		direction = pygame.math.Vector2(target) - pygame.math.Vector2(position)
		self.velocity = direction.normalize() * self.speed

	def update(self):
		self.rect.x += self.velocity.x
		self.rect.y += self.velocity.y
		if self.rect.right < 0 or self.rect.left > get_screen_width() or self.rect.bottom < 0 or self.rect.top > get_screen_height():
			self.kill()

def main():
	pygame.init()
	global font
	font = pygame.font.SysFont(None, 55)
	window()

def window():
	screen = pygame.display.set_mode((get_screen_width(), get_screen_height()))
	pygame.display.set_caption("Kitty Skies")
	flap_sound = pygame.mixer.Sound('assets/sonidoBOING.mp3')  # Ruta al archivo de sonido
	flap_sound.set_volume(1.5)  # Ajusta el volumen
	pygame.mixer.music.load('assets/kittySkiesMusic.mp3')  # Cargar música
	pygame.mixer.music.play(-1)  # Reproduce en bucle infinito

	bird = Bird(screen)
	all_sprites = pygame.sprite.Group(bird)
	pipes = pygame.sprite.Group()
	boxes = pygame.sprite.Group()
	shooter_boxes = pygame.sprite.Group()
	bullets = pygame.sprite.Group()
	clock = pygame.time.Clock()
	score = 0
	level = 1
	pipe_timer = 0
	max_pipes = 50
	box_spawned = False
	shooter_timer = 0

	byte_image = pygame.image.load('assets/byte.png')
	byte_image = pygame.transform.scale(byte_image, (70, 90))

	background1 = pygame.image.load('assets/fondoOpcion1.jpg').convert()
	background2 = pygame.image.load('assets/fondoOpcion2.jpg').convert()

	running = True
	while running:
		for event in pygame.event.get():
			if event.type == pygame.QUIT:
				running = False
			if event.type == pygame.KEYDOWN:
				if event.key == pygame.K_SPACE:
					bird.flap()
					flap_sound.play()

		all_sprites.update()
		pipes.update()
		boxes.update()
		shooter_boxes.update()
		bullets.update()

		if pygame.sprite.spritecollideany(bird, pipes) or pygame.sprite.spritecollideany(bird, bullets):
			running = False
			pygame.mixer.music.stop()
			game_over(screen)

		if bird.rect.top <= 0 or bird.rect.bottom >= get_screen_height():
			running = False
			pygame.mixer.music.stop()
			game_over(screen)

		if score >= max_pipes or score < 50:
			pipe_timer += clock.get_time()
			if pipe_timer > get_pipe_frequency():
				pipe_timer = 0
				pipe_y = random.randint(-get_pipe_height() + 100, -100)
				top_pipe = Pipe(get_screen_width(), pipe_y, True)
				bottom_pipe = Pipe(get_screen_width(), pipe_y + get_pipe_height(), False)
				all_sprites.add(top_pipe, bottom_pipe)
				pipes.add(top_pipe, bottom_pipe)
				score += 1
				if score == 25:
					level = 2

		if score >= max_pipes and not box_spawned:
			box = Box()
			all_sprites.add(box)
			boxes.add(box)
			box_spawned = True

		if pygame.sprite.spritecollideany(bird, boxes):
			running = False
			pygame.mixer.music.stop()
			you_win(screen)

		if level == 2 and score < max_pipes:
			shooter_timer += clock.get_time()
			if shooter_timer > 3000:
				shooter_timer = 0
				position = random.choice(['top', 'bottom'])
				shooter_box = ShooterBox(position)
				all_sprites.add(shooter_box)
				shooter_boxes.add(shooter_box)
				bullet = shooter_box.shoot(bird.rect.center)
				all_sprites.add(bullet)
				bullets.add(bullet)

		for shooter_box in shooter_boxes:
			if shooter_box.has_shot and not any(bullet.alive() for bullet in bullets):
				shooter_box.kill()

		if level == 2:
			screen.blit(background1, (-300, -300))
		else:
			screen.blit(background2, (-300, -300))

		all_sprites.draw(screen)
		score_text = font.render(f"Score: {score}", True, get_white())
		level_text = font.render(f"Level: {level}", True, get_white())
		screen.blit(score_text, (10, 10))
		screen.blit(level_text, (10, 50))
		screen.blit(byte_image, (10, get_screen_height() - 90))
		pygame.display.flip()

		clock.tick(60)

	pygame.quit()

if __name__ == '__main__': 
	def show_menu(screen, message):
		screen.fill(get_black())
		font = pygame.font.SysFont(None, 75)
		text = font.render(message, True, get_white())
		text_rect = text.get_rect(center=(get_screen_width() // 2, get_screen_height() // 2 - 50))
		screen.blit(text, text_rect)

		button_font = pygame.font.SysFont(None, 55)
		button_text = button_font.render("Play Again", True, get_white())
		button_rect = button_text.get_rect(center=(get_screen_width() // 2, get_screen_height() // 2 + 50))
		screen.blit(button_text, button_rect)

		pygame.display.flip()
		return button_rect

	def main_menu():
		pygame.init()
		screen = pygame.display.set_mode((get_screen_width(), get_screen_height()))
		pygame.display.set_caption("Kitty Skies")
		button_rect = show_menu(screen, "Kitty Skies")

		running = True
		while running:
			for event in pygame.event.get():
				if event.type == pygame.QUIT:
					pygame.quit()
					exit()
				if event.type == pygame.MOUSEBUTTONDOWN:
					if button_rect.collidepoint(event.pos):
						running = False

		main()

	def game_over(screen):
		button_rect = show_menu(screen, "Game Over")
		running = True
		while running:
			for event in pygame.event.get():
				if event.type == pygame.QUIT:
					pygame.quit()
					exit()
				if event.type == pygame.MOUSEBUTTONDOWN:
					if button_rect.collidepoint(event.pos):
						running = False

		main()

	def you_win(screen):
		button_rect = show_menu(screen, "You Win!")
		running = True
		while running:
			for event in pygame.event.get():
				if event.type == pygame.QUIT:
					pygame.quit()
					exit()
				if event.type == pygame.MOUSEBUTTONDOWN:
					if button_rect.collidepoint(event.pos):
						running = False

		main()

	if __name__ == '__main__':
		main_menu()